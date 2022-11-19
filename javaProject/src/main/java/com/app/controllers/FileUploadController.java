package com.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.entities.DatabaseFile;
import com.app.entities.*;
import com.app.services.DatabaseFileService;
@CrossOrigin(origins = "*")
@RestController
public class FileUploadController {

    @Autowired
    private DatabaseFileService fileStorageService;

    @PostMapping("/uploadFile")
    public Responsef uploadFile(@RequestParam("file") MultipartFile file) {
    	DatabaseFile fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new Responsef(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }


}