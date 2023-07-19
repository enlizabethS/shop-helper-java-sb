package com.shophelperjavasb.shophelperjavasb.products.dto;

import com.shophelperjavasb.shophelperjavasb.products.model.Image;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class ImageResponseDto {
    @Schema(defaultValue = "id of the image")
    private Long id;
    @Schema(defaultValue = "image creation date")
    private LocalDateTime createdDate;


    public static ImageResponseDto from(Image image) {
        return ImageResponseDto.builder()
            .id(image.getId())
            .createdDate(image.getCreatedDate())
            .build();
    }

    public static List<ImageResponseDto> from(List<Image> images) {
        return images.stream()
            .map(ImageResponseDto::from)
            .collect(Collectors.toList());
    }
}
