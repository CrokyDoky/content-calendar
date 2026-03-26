package com.example.content_calendar.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorageService {
    public final Path uploadPath;

    public FileStorageService(@Value("${app.upload-dir}") String uploadDir) {
        this.uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't create directory for upload");
        }
    }

    public String storeFile(MultipartFile file) {
        if (file.isEmpty())
            throw new IllegalArgumentException("File cannot be empty");

        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/"))
            throw new IllegalArgumentException("Only images allowed");

        String originalFilename = file.getOriginalFilename();
        String extension = Objects.requireNonNull(originalFilename)
                .substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + extension;

        try {
            Path targetPath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), targetPath,
                    StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("Error saving file", e);
        }
    }

    public void deleteFile(String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String filename = imageUrl .substring(imageUrl.lastIndexOf("/") + 1);
            Path filePath = uploadPath.resolve(filename).normalize();
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                System.out.println("Couldn't delete file" + filename);
            }
        }
    }
}
