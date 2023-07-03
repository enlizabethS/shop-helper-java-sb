package com.shophelperjavasb.shophelperjavasb.products.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tags(value = {
        @Tag(name = "Image")
})
@RequestMapping("/api/image")
public interface ImageApi {
    @GetMapping("/{id}")
    @Operation(summary = "Get images")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))
    ResponseEntity<InputStreamResource> getImageById(@PathVariable Long id);
}
