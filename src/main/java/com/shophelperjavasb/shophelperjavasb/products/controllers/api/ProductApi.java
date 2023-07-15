package com.shophelperjavasb.shophelperjavasb.products.controllers.api;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tags(value = {
    @Tag(name = "Products")
})
@RequestMapping("/api/products")
public interface ProductApi {
    @Operation(summary = "Create", description = "Create new product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "New product",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class))})}
    )
    @PostMapping
    ResponseEntity<ProductDto> createProduct(
        @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser,
        @RequestParam("productName") String productName,
        @RequestParam("quantity") String quantity,
        @RequestParam("price") String price,
        @RequestParam(name = "file1", required = false) MultipartFile file1,
        @RequestParam(name = "file2", required = false) MultipartFile file2,
        @RequestParam(name = "file3", required = false) MultipartFile file3
    ) throws IOException;

//    @GetMapping("/")
//    @Operation(summary = "Get products")
//    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)))
//    List<ProductDto> getAllProducts();


//    @GetMapping("/{id}")
//    @Operation(summary = "Get product info")
//    @ApiResponse(responseCode = "200", content =@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)))
//    ProductDto getProductInfo(@PathVariable Long id);

//    @DeleteMapping("/delete/{id}")
//    @Operation(summary = "Delete a product")
//    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)))
//    void deleteProduct(@PathVariable Long id);
}
