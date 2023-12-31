package com.shophelperjavasb.shophelperjavasb.products.controllers.api;

import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.products.dto.FilterTitleDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductDto;
import com.shophelperjavasb.shophelperjavasb.products.dto.ProductPreviewDto;
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
import java.util.List;

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
        @RequestParam("description") String description,
        @RequestParam(name = "file1", required = false) MultipartFile file1,
        @RequestParam(name = "file2", required = false) MultipartFile file2,
        @RequestParam(name = "file3", required = false) MultipartFile file3
    ) throws IOException;

    @Operation(summary = "Get by id", description = "Get product by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class))})}
    )
    @GetMapping("/{product-id}")
    ResponseEntity<ProductDto> getById(@PathVariable("product-id") Long productId);

    @Operation(summary = "Find products", description = "Find products by title")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Find products by title",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class))})}
    )
    @PostMapping("/find")
    ResponseEntity<List<ProductPreviewDto>> findProductsByTitle(
        @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser,
        @RequestBody FilterTitleDto filter
    );

    @Operation(summary = "Delete product", description = "Delete product by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Delete product by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDto.class))})}
    )
    @DeleteMapping("/{product-id}")
    void delete(@PathVariable("product-id") Long productId);
}
