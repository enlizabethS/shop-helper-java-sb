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
public class ImageDto {
    @Schema(defaultValue = "id of the image")
    private Long id;
    private LocalDateTime createdDate;


    public static ImageDto from(Image image) {
        return ImageDto.builder()
            .id(image.getId())
            .createdDate(image.getCreatedDate())
            .build();
    }

    public static List<ImageDto> from(List<Image> images) {
        return images.stream()
            .map(ImageDto::from)
            .collect(Collectors.toList());
    }
}
