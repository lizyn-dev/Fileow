package org.zlycerqan.fileow.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.zlycerqan.fileow.minio.MinioService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    private final MinioService minioService;

    public List<Item> getListByChannel(String channel) {
        return itemRepository.findByChannelOrderByCreateTimeDesc(channel);
    }

    public Item addText(String channel, String title, String text) {
        TextItem textItem = new TextItem();
        textItem.setType(ItemType.text);
        textItem.setChannel(channel);
        textItem.setTitle(title);
        textItem.setText(text);
        itemRepository.save(textItem);
        return textItem;
    }

    @Transactional
    public Item addFile(String channel, MultipartFile file) {
        FileItem fileItem = new FileItem();
        fileItem.setChannel(channel);
        fileItem.setType(ItemType.file);
        fileItem.setFilename(file.getOriginalFilename());
        fileItem.setSize(file.getSize());
        itemRepository.save(fileItem);
        try {
            if (!minioService.addFile(fileItem.getId(), file.getInputStream(), file.getSize(), 5)) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
        return fileItem;
    }

    public String getDownloadLink(String id) {
        Optional<Item> fileItem = itemRepository.findById(id);
        if (fileItem.isEmpty()) {
            return null;
        }
        FileItem file = (FileItem) fileItem.get();
        return minioService.signDownloadLink(id, file.getFilename());
    }

    public Boolean removeItem(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) {
            return false;
        }
        if (item.get().getType() == ItemType.file) {
            boolean status = minioService.delete(item.get().getId());
            if (!status) {
                return false;
            }
        }
        itemRepository.deleteById(id);
        return true;
    }

}
