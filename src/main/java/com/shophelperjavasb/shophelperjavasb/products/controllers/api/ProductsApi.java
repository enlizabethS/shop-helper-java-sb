package com.shophelperjavasb.shophelperjavasb.products.controllers.api;

import com.shophelperjavasb.shophelperjavasb.products.dto.NewProductDTO;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDTO;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductNameDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductProfileDTO;
import com.shophelperjavasb.shophelperjavasb.products.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Tags(value = {
        @Tag(name = "Products")
})
@RequestMapping("/api/products")
public interface ProductsApi {

    @Operation(summary = "Get products", description = "Get products current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Products current user",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductProfileDTO.class))})}
    )
    @GetMapping
    ResponseEntity<List<ProductProfileDTO>> productsByName(ProductNameDto productName);

    @Operation(summary = "Get product", description = "Get product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get product",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductProfileDTO.class))})}
    )
    @GetMapping("/{product-id}")
    ResponseEntity<ProductProfileDTO> productById(@PathVariable("product-id") Long productId);

    @Operation(summary = "Get info", description = "Get info of the product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get info of the product",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class))})}
    )
    @GetMapping("/{product-id}/info")
    ResponseEntity<ProductDTO> productInfo(@PathVariable("product-id") Long productId);

    @Operation(summary = "Create", description = "Create a new product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Create a new product",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductProfileDTO.class))})}
    )
    @PostMapping
    ResponseEntity<ProductProfileDTO> createProduct(
            @Parameter(description = "File 1") @RequestParam("file1") MultipartFile file1,
            @Parameter(description = "File 2") @RequestParam("file2") MultipartFile file2,
            @Parameter(description = "File 3") @RequestParam("file3") MultipartFile file3,
            @RequestBody Product product,
            Principal principal
    ) throws IOException;
    
    @Operation(summary = "Delete", description = "Delete a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Delete a product",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class))})}
    )
    @DeleteMapping("{product-id}")
    void deleteProduct(@PathVariable("product-id") Long productId);
}
