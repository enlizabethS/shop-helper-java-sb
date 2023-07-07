package com.shophelperjavasb.shophelperjavasb.products.controllers.api;

import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tags(value = {
        @Tag(name = "Product")
})
@RequestMapping("/api/product")
public interface ProductApi {

    @GetMapping("/")
    @Operation(summary = "Get products")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)))
    List<ProductDTO> getAllProducts();


    @GetMapping("/{id}")
    @Operation(summary = "Get product info")
    @ApiResponse(responseCode = "200", content =@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)))
    ProductDTO getProductInfo(@PathVariable Long id);

    @PostMapping("/create")
    @Operation(summary = "Create a new product")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)))
    ProductDTO createProduct(@RequestBody ProductDTO productDto);

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a product")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)))
    void deleteProduct(@PathVariable Long id);
    @GetMapping("/users/{userId}/products")
    @Operation(summary = "Get user products")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)))
    List<ProductDTO> getUserProducts(@PathVariable Long userId);

}
