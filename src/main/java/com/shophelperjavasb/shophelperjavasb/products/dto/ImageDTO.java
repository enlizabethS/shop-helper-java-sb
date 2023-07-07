package com.shophelperjavasb.shophelperjavasb.products.dto;

import com.shophelperjavasb.shophelperjavasb.products.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ImageDTO {
    private Long id;
    private String url;
    private String title;


    public static ImageDTO from(Image image){
        return ImageDTO.builder()
                .id(image.getId())
                .title(image.getTitle())
                .url(image.getUrl())
                .build();
    }
    public static List<ImageDTO> from(List <Image> images){
        return images.stream()
                .map(ImageDTO::from)
                .collect(Collectors.toList());
    }
}
