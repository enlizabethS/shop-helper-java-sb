package com.shophelperjavasb.shophelperjavasb.addresses.services;

import com.shophelperjavasb.shophelperjavasb.addresses.dto.AddressDto;
import com.shophelperjavasb.shophelperjavasb.addresses.dto.NewAddressDto;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;

public interface AddressesService {
    AddressDto createNew(
        AuthenticatedUser currentUser,
        NewAddressDto newAddressDto
    );

    AddressDto getById(Long addressId);

    AddressDto update(
        Long addressId,
        NewAddressDto newAddressDto
    );

    void delete(Long addressId);
}
