package org.zlycerqan.fileow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FileowApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileowApplication.class, args);
    }

}
