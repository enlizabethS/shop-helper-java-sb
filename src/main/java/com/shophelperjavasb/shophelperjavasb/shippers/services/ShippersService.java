package com.shophelperjavasb.shophelperjavasb.shippers.services;

import com.shophelperjavasb.shophelperjavasb.shippers.dto.ShippersDto;
import com.shophelperjavasb.shophelperjavasb.shippers.model.Shipper;
import com.shophelperjavasb.shophelperjavasb.shippers.repositories.ShippersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippersService {
    private final ShippersRepository shippersRepository;


    public ShippersDto getShipperById(Long id) {
        Shipper shipper = shippersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Shipper с ID " + id + " not found"));

        return mapShipperToDTO(shipper);
    }

    public ShippersDto createShipper(ShippersDto shipperDTO) {
        Shipper shipper = new Shipper();
        shipper.setShipperName(shipperDTO.getShipperName());
        shipper.setPhone(shipperDTO.getPhone());
        shipper.setSelfPickUp(shipperDTO.isSelfPickUp());

        Shipper savedShipper = shippersRepository.save(shipper);

        return mapShipperToDTO(savedShipper);
    }
    public ShippersDto updateShipper(Long id, ShippersDto shipperDTO) {
        Shipper shipper = shippersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Shipper с ID " + id + " not found"));

        shipper.setShipperName(shipperDTO.getShipperName());
        shipper.setPhone(shipperDTO.getPhone());
        shipper.setSelfPickUp(shipperDTO.isSelfPickUp());

        Shipper updatedShipper = shippersRepository.save(shipper);

        return mapShipperToDTO(updatedShipper);
    }
    private ShippersDto mapShipperToDTO(Shipper shipper) {
        ShippersDto shipperDTO = new ShippersDto();
        shipperDTO.setId(shipper.getId());
        shipperDTO.setShipperName(shipper.getShipperName());
        shipperDTO.setPhone(shipper.getPhone());
        shipperDTO.setSelfPickUp(shipper.isSelfPickUp());
        return shipperDTO;
    }

}
