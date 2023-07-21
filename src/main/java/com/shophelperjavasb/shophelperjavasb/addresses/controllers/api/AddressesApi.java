package com.shophelperjavasb.shophelperjavasb.addresses.controllers.api;

import com.shophelperjavasb.shophelperjavasb.addresses.dto.AddressDto;
import com.shophelperjavasb.shophelperjavasb.addresses.dto.NewAddressDto;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
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

@Tags(value = {
    @Tag(name = "Addresses")
})
@RequestMapping("/api/addresses")
public interface AddressesApi {
    @Operation(summary = "Create", description = "Create new address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "New address",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AddressDto.class))})}
    )
    @PostMapping
    ResponseEntity<AddressDto> createNew(
        @Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser currentUser,
        @RequestBody NewAddressDto newAddressDto);

    @Operation(summary = "Get by id", description = "Get address by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "address by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AddressDto.class))})}
    )
    @GetMapping("/{address-id}")
    ResponseEntity<AddressDto> getById(@PathVariable("address-id") Long addressId);

    @Operation(summary = "Update addresses", description = "Update address by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Update address by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AddressDto.class))})}
    )
    @PutMapping("/{address-id}")
    ResponseEntity<AddressDto> update(@PathVariable("address-id") Long addressId, @RequestBody NewAddressDto newAddressDto);

    @Operation(summary = "Delete address", description = "Delete address by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Delete address by id",
            content = {
                @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AddressDto.class))})}
    )
    @DeleteMapping("/{address-id}")
    void delete(@PathVariable("address-id") Long addressId);
}
