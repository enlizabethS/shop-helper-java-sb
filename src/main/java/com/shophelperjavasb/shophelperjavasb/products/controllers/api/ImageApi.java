package com.shophelperjavasb.shophelperjavasb.products.controllers.api;

import com.shophelperjavasb.shophelperjavasb.products.dto.ImageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tags(value = {
        @Tag(name = "Image")
})
@RequestMapping("/api/image")
public interface ImageApi {
    @GetMapping("/images")
    @Operation(summary = "Get all images")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageDTO.class)))
    ResponseEntity<InputStreamResource> getAllImages();

    @PostMapping ("/create")
    @Operation(summary = "Create photo")
    @ApiResponse (responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageDTO.class)))
    ImageDTO createImage(@RequestBody ImageDTO imageDto);

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing image")
    @ApiResponse (responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageDTO.class)))
    ImageDTO updateImage(@PathVariable Long id, @RequestBody ImageDTO imageDto);

    @DeleteMapping("/{id}")
    @Operation(summary ="Delete an image")
    @ApiResponse (responseCode = "200",content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageDTO.class)))
    void deleteImage(@PathVariable Long id);
}
