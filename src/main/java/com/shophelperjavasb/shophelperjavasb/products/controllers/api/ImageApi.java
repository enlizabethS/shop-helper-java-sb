package com.shophelperjavasb.shophelperjavasb.products.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tags(value = {
    @Tag(name = "Images")
})
@RequestMapping("/api/images")
public interface ImageApi {
    @Operation(summary = "Add image", description = "Add new image")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Add new image",
            content = {
                @Content(mediaType = "application/json")})}
    )
    @PostMapping
    ResponseEntity<?> addImg(@RequestParam("file") MultipartFile file) throws IOException;

    @Operation(summary = "Get image", description = "Get image by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get image by id",
            content = {
                @Content(mediaType = "application/json")})}
    )
    @GetMapping("/{img-id}")
    ResponseEntity<?> getById(@PathVariable("img-id") Long imageId);
}
