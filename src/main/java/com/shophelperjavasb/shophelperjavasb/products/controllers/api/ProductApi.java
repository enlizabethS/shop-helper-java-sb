package com.shophelperjavasb.shophelperjavasb.products.controllers.api;

import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Tags(value = {
        @Tag(name = "Product")
})
@RequestMapping("/api/product")
public interface ProductApi {

    @GetMapping("/")
    @Operation(summary = "Get products")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))
    String products(
            @RequestParam(name = "searchWord", required = false) String title,
            Principal principal,
            Model model
    );

    @GetMapping("/{id}")
    @Operation(summary = "Get product info")
    @ApiResponse(responseCode = "200", content =@Content(mediaType = "application/json"))
    String productInfo(
            @Parameter(description = "Product ID") @PathVariable Long id,
            Model model,
            Principal principal
    );
    @PostMapping("/create")
    @Operation(summary = "Create a new product")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))
    String createProduct(
            @Parameter(description = "File 1") @RequestParam("file1") MultipartFile file1,
            @Parameter(description = "File 2") @RequestParam("file2") MultipartFile file2,
            @Parameter(description = "File 3") @RequestParam("file3") MultipartFile file3,
            @RequestBody Product product,
            Principal principal
    ) throws IOException;

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a product")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))
    String deleteProduct(
            @Parameter(description = "Product ID") @PathVariable Long id,
            Principal principal
    );
    @GetMapping("/my/products")
    @Operation(summary = "Get user products")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))
    String userProducts(
            Principal principal,
            Model model
    );

}
