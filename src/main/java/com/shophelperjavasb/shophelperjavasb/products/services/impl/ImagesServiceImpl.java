package com.shophelperjavasb.shophelperjavasb.products.services.impl;

import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.products.dto.ImageDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ImageResponseDto;
import com.shophelperjavasb.shophelperjavasb.products.model.Image;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ImagesRepository;
import com.shophelperjavasb.shophelperjavasb.products.services.ImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImagesServiceImpl implements ImagesService {
    private final ImagesRepository imagesRepository;

    @Override
    public ImageResponseDto addImg(MultipartFile file) throws IOException {
        Image image = new Image();

        if (file.getSize() != 0) image = Image.toEntity(file);

        imagesRepository.save(image);

        return ImageResponseDto.from(image);
    }

    @Override
    public ImageDto getById(Long imageId) {
        Image image = imagesRepository.findById(imageId)
            .orElseThrow(() -> new NotFoundException("Image with id <" + imageId + "> not found"));

        return ImageDto.from(image);
    }
}
