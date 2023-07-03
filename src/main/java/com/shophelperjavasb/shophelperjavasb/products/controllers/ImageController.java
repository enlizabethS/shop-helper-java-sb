package com.shophelperjavasb.shophelperjavasb.products.controllers;

import com.shophelperjavasb.shophelperjavasb.products.controllers.api.ImageApi;
import com.shophelperjavasb.shophelperjavasb.products.model.Image;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImageController implements ImageApi {
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<InputStreamResource> getImageById(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
