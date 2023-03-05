package org.zlycerqan.fileow.minio;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MinioService {
    private final MinioConfig minioConfig;

    private final MinioClient minioClient;

    public Boolean addFile(String id, InputStream inputStream, Long size, Integer retry) {
        for (int i = 0; i < retry; ++ i) {
            try {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(minioConfig.getBucket())
                                .object(id)
                                .contentType("application/octet-stream")
                                .stream(inputStream, size, -1)
                                .build()
                );
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public String signDownloadLink(String id, String filename) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("response-content-disposition",  String.format("attachment; filename=\"%s\"", URLEncoder.encode(filename, StandardCharsets.UTF_8)));
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .expiry(2 * 60 * 60)
                            .bucket(minioConfig.getBucket())
                            .method(Method.GET)
                            .object(id)
                            .extraQueryParams(headers)
                            .build()
            );
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean delete(String id) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minioConfig.getBucket())
                            .object(id)
                            .build()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
