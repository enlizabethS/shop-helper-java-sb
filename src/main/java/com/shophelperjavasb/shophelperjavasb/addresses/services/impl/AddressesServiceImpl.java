package com.shophelperjavasb.shophelperjavasb.addresses.services.impl;

import com.shophelperjavasb.shophelperjavasb.addresses.dto.AddressDto;
import com.shophelperjavasb.shophelperjavasb.addresses.dto.NewAddressDto;
import com.shophelperjavasb.shophelperjavasb.addresses.model.Address;
import com.shophelperjavasb.shophelperjavasb.addresses.repositories.AddressRepository;
import com.shophelperjavasb.shophelperjavasb.addresses.services.AddressesService;
import com.shophelperjavasb.shophelperjavasb.config.details.AuthenticatedUser;
import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.users.model.User;
import com.shophelperjavasb.shophelperjavasb.users.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddressesServiceImpl implements AddressesService {
    private final AddressRepository addressRepository;
    private final UsersRepository usersRepository;

    @Override
    public AddressDto createNew(AuthenticatedUser currentUser, NewAddressDto newAddressDto) {
        User currUser = currentUser.getUser();

        User user = usersRepository.findById(currUser.getId()).get();

        Address address = Address.builder()
            .user(currUser)
            .street(newAddressDto.getStreet())
            .houseNumber(newAddressDto.getHouseNumber())
            .city(newAddressDto.getCity())
            .postalCode(newAddressDto.getPostalCode())
            .country(newAddressDto.getCountry())
            .createdDate(LocalDateTime.now())
            .build();

        addressRepository.save(address);

        user.setAddress(address);

        usersRepository.save(user);

        return AddressDto.from(address);
    }

    @Override
    public AddressDto getById(Long addressId) {
        Address address = addressRepository.findById(addressId)
            .orElseThrow(() -> new NotFoundException("Auction with id <" + addressId + "> not found"));

        return AddressDto.from(address);
    }

    @Override
    public AddressDto update(Long addressId, NewAddressDto newAddressDto) {
        Address address = addressRepository.findById(addressId)
            .orElseThrow(() -> new NotFoundException("Auction with id <" + addressId + "> not found"));

        address.setStreet(newAddressDto.getStreet());
        address.setHouseNumber(newAddressDto.getHouseNumber());
        address.setCity(newAddressDto.getCity());
        address.setPostalCode(newAddressDto.getPostalCode());
        address.setCountry(newAddressDto.getCountry());

        addressRepository.save(address);

        return AddressDto.from(address);
    }

    @Override
    public void delete(Long addressId) {
        if (addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
        } else {
            throw new NotFoundException("Address <" + addressId + "> not found");
        }
    }
}
