package com.shophelperjavasb.shophelperjavasb.products.dto;

import com.shophelperjavasb.shophelperjavasb.products.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ImageDto {
    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    private byte[] bytes;
    private LocalDateTime createdDate;

    public static ImageDto from(Image image) {
        return ImageDto.builder()
            .id(image.getId())
            .name(image.getName())
            .originalFileName(image.getOriginalFileName())
            .size(image.getSize())
            .contentType(image.getContentType())
            .bytes(image.getBytes())
            .createdDate(image.getCreatedDate())
            .build();
    }

    public static List<ImageDto> from(List<Image> images) {
        return images.stream()
            .map(ImageDto::from)
            .collect(Collectors.toList());
    }
}
