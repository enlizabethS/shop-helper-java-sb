package com.shophelperjavasb.shophelperjavasb.products.controllers;

import com.shophelperjavasb.shophelperjavasb.products.controllers.api.ImageApi;
import com.shophelperjavasb.shophelperjavasb.products.dto.ImageDTO;
import com.shophelperjavasb.shophelperjavasb.products.services.impl.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageController implements ImageApi {
    private final ImageServiceImpl imageService;

    @Override
    public ResponseEntity<InputStreamResource> getAllImages() {
        return (ResponseEntity<InputStreamResource>) imageService.getAllImages();
    }

    @Override
    public ImageDTO createImage(ImageDTO imageDto) {
        return imageService.createImage(imageDto);
    }
    @Override
    public ImageDTO updateImage( Long id,ImageDTO imageDto) {
        return imageService.updateImage(id, imageDto);
    }
    @Override
    public void deleteImage( Long id) {
        imageService.deleteImage(id);
    }
}
