package com.shophelperjavasb.shophelperjavasb.products.controllers.api;

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
    @PostMapping
    ResponseEntity<?> addImg(@RequestParam("file")MultipartFile file) throws IOException;

    @GetMapping("/{img-id}")
    ResponseEntity<?> getById(@PathVariable("img-id") Long imageId);
}
