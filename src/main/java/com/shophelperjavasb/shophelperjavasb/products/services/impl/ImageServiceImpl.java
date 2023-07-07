package com.shophelperjavasb.shophelperjavasb.products.services.impl;

import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.products.dto.ImageDTO;
import com.shophelperjavasb.shophelperjavasb.products.model.Image;
import com.shophelperjavasb.shophelperjavasb.products.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ImageServiceImpl {

    private final ImageRepository imageRepository;

    public List<ImageDTO> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return images.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ImageDTO convertToDto(Image image) {
        ImageDTO imageDto = new ImageDTO();
        imageDto.setId(image.getId());
        imageDto.setUrl(image.getUrl());
        imageDto.setTitle(image.getTitle());
        return imageDto;
    }

    public ImageDTO createImage(ImageDTO imageDto) {
        Image image = convertToEntity(imageDto);
        Image savedImage = imageRepository.save(image);
        return convertToDto(savedImage);
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    public ImageDTO updateImage(Long id, ImageDTO imageDto) {
        Image existingImage = imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Image not found"));
        existingImage.setTitle(imageDto.getTitle());
        existingImage.setUrl(imageDto.getUrl());

        Image updatedImage = imageRepository.save(existingImage);
        return convertToDto(updatedImage);
    }
    private Image convertToEntity(ImageDTO imageDto) {
        Image image = new Image();
        image.setTitle(imageDto.getTitle());
        image.setUrl(imageDto.getUrl());
        // Set other fields
        return image;
    }
}
