package org.sid.multifileupload;

import org.sid.multifileupload.service.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class MultiFileUploadApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MultiFileUploadApplication.class, args);
    }

    @Resource
    FilesStorageService storageService;

    // run the following commands in the terminal to initialize the upload directory "mvn spring-boot:run"
    @Override
    public void run(String... arg) throws Exception {
        storageService.deleteAll();
        storageService.init();
    }

}
