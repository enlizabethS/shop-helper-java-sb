package com.shophelperjavasb.shophelperjavasb.shippers.services;

import com.shophelperjavasb.shophelperjavasb.exceptions.NotFoundException;
import com.shophelperjavasb.shophelperjavasb.shippers.dto.ShippersDTO;
import com.shophelperjavasb.shophelperjavasb.shippers.model.Shipper;
import com.shophelperjavasb.shophelperjavasb.shippers.repositories.ShippersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShippersService {
    private final ShippersRepository shippersRepository;


    public ShippersDTO getShipperById(Long id) {
        Shipper shipper = shippersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Shipper с ID " + id + " не найден"));

        return mapShipperToDTO(shipper);
    }

    public ShippersDTO createShipper(ShippersDTO shipperDTO) {
        Shipper shipper = new Shipper();
        shipper.setShipperName(shipperDTO.getShipperName());
        shipper.setPhone(shipperDTO.getPhone());
        shipper.setSelfPickUp(shipperDTO.isSelfPickUp());

        Shipper savedShipper = shippersRepository.save(shipper);

        return mapShipperToDTO(savedShipper);
    }
    public ShippersDTO updateShipper(Long id, ShippersDTO shipperDTO) {
        Shipper shipper = shippersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Shipper с ID " + id + " не найден"));

        shipper.setShipperName(shipperDTO.getShipperName());
        shipper.setPhone(shipperDTO.getPhone());
        shipper.setSelfPickUp(shipperDTO.isSelfPickUp());

        Shipper updatedShipper = shippersRepository.save(shipper);

        return mapShipperToDTO(updatedShipper);
    }
    private ShippersDTO mapShipperToDTO(Shipper shipper) {
        ShippersDTO shipperDTO = new ShippersDTO();
        shipperDTO.setId(shipper.getId());
        shipperDTO.setShipperName(shipper.getShipperName());
        shipperDTO.setPhone(shipper.getPhone());
        shipperDTO.setSelfPickUp(shipper.isSelfPickUp());
        return shipperDTO;
    }

}
