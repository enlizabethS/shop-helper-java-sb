package com.shophelperjavasb.shophelperjavasb.addresses.controllers;

import com.shophelperjavasb.shophelperjavasb.addresses.controllers.api.AddressesApi;
import com.shophelperjavasb.shophelperjavasb.addresses.dto.AddressDto;
import com.shophelperjavasb.shophelperjavasb.addresses.dto.NewAddressDto;
import com.shophelperjavasb.shophelperjavasb.addresses.services.AddressesService;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressesController implements AddressesApi {
    private final AddressesService addressesService;

    @Override
    public ResponseEntity<AddressDto> createNew(AuthenticatedUser currentUser, NewAddressDto newAddressDto) {
        return ResponseEntity.status(201)
            .body(addressesService.createNew(currentUser, newAddressDto));
    }

    @Override
    public ResponseEntity<AddressDto> getById(Long addressId) {
        return ResponseEntity.ok(addressesService.getById(addressId));
    }

    @Override
    public ResponseEntity<AddressDto> update(Long addressId, NewAddressDto newAddressDto) {
        return ResponseEntity.ok(addressesService.update(addressId, newAddressDto));
    }

    @Override
    public void delete(Long addressId) {
        addressesService.delete(addressId);
    }
}
