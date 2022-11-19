package com.app.services;


import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import com.app.exceptions.FileNotFoundException;
import com.app.exceptions.FileStorageException;
import com.app.entities.DatabaseFile;
import com.app.daos.DatabaseFileRepository;

@Service
public class DatabaseFileService {

    @Autowired
    private DatabaseFileRepository dbFileRepository;
    
    private int userId;
    
    private int randomNumber;
    
    public DatabaseFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());
            Random random = new Random();   		
    		randomNumber = random.nextInt(1000000);  
    		System.out.println("random number: "+randomNumber);
            dbFile.setCode(randomNumber);
//            dbFile.setUserId();
//            return dbFileRepository.save(dbFile);
            return dbFile;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DatabaseFile getFile(int fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}