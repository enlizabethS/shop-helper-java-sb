package com.shophelperjavasb.shophelperjavasb.products.services;

import com.shophelperjavasb.shophelperjavasb.products.dto.ImageDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImagesService {
    ImageResponseDto addImg(MultipartFile file) throws IOException;

    ImageDto getById(Long imageId);
}
