package com.shophelperjavasb.shophelperjavasb.shippers.controllers.api;

import com.shophelperjavasb.shophelperjavasb.shippers.dto.ShippersDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tags(value = {
        @Tag(name = "Shippers")
})
@RequestMapping("/api/shippers")
public interface ShippersApi {
        @GetMapping("/{id}")
        @Operation(summary = "Get a Shipper by ID", description = "Get a Shipper by providing its ID")
        @ApiResponse(responseCode = "200", description = "Shipper found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShippersDTO.class)))
        @ApiResponse(responseCode = "404", description = "Shipper not found")
        ResponseEntity<ShippersDTO> getShipperById(
                @Parameter(description = "ID of the Shipper to be obtained. Cannot be empty.", required = true)
                @PathVariable Long id);

        @PostMapping
        @Operation(summary = "Create a new Shipper", description = "Create a new Shipper with the provided details")
        @ApiResponse(responseCode = "201", description = "Shipper created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShippersDTO.class)))
        ResponseEntity<ShippersDTO> createShipper(
                @Parameter(description = "Shipper object that needs to be added", required = true)
                @RequestBody ShippersDTO shipperDTO);

        @PutMapping("/update/{id}")
        @Operation(summary = "Update an existing Shipper", description = "Update an existing Shipper with the provided details")
        @ApiResponse(responseCode = "200", description = "Shipper updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShippersDTO.class)))
        @ApiResponse(responseCode = "404", description = "Shipper not found")
        ResponseEntity<ShippersDTO> updateShipper(
                @Parameter(description = "ID of the Shipper to be updated. Cannot be empty.", required = true)
                @PathVariable Long id,
                @Parameter(description = "Updated Shipper object", required = true)
                @RequestBody ShippersDTO shipperDTO);
    }


