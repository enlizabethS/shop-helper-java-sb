package com.shophelperjavasb.shophelperjavasb.products.controllers;

import com.shophelperjavasb.shophelperjavasb.products.controllers.api.ImageApi;

import com.shophelperjavasb.shophelperjavasb.products.dto.ImageDto;
import com.shophelperjavasb.shophelperjavasb.products.services.ImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController implements ImageApi {
    private final ImagesService imagesService;

    @Override
    public ResponseEntity<?> addImg(MultipartFile file) throws IOException {
        return ResponseEntity.status(201)
            .body(imagesService.addImg(file));
    }

    @Override
    public ResponseEntity<?> getById(Long imageId) {
        ImageDto imageDto = imagesService.getById(imageId);

        return ResponseEntity.ok()
            .header("fileName", imageDto.getOriginalFileName())
            .contentType(MediaType.valueOf(imageDto.getContentType()))
            .contentLength(imageDto.getSize())
            .body(new InputStreamResource(new ByteArrayInputStream(imageDto.getBytes())));
    }
}
