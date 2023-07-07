package com.shophelperjavasb.shophelperjavasb.shippers.controllers;

import com.shophelperjavasb.shophelperjavasb.shippers.controllers.api.ShippersApi;
import com.shophelperjavasb.shophelperjavasb.shippers.dto.ShippersDTO;
import com.shophelperjavasb.shophelperjavasb.shippers.services.ShippersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShippersController implements ShippersApi {
    private final ShippersService shipperService;

    @Override
    public ResponseEntity<ShippersDTO> getShipperById(@PathVariable Long id) {
        ShippersDTO shipperDTO = shipperService.getShipperById(id);
        if (shipperDTO != null) {
            return new ResponseEntity<>(shipperDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ShippersDTO> createShipper(@RequestBody ShippersDTO shipperDTO) {
        ShippersDTO createdShipper = shipperService.createShipper(shipperDTO);
        return new ResponseEntity<>(createdShipper, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ShippersDTO> updateShipper(@PathVariable Long id, @RequestBody ShippersDTO shipperDTO) {
        ShippersDTO updatedShipper = shipperService.updateShipper(id, shipperDTO);
        if (updatedShipper != null) {
            return new ResponseEntity<>(updatedShipper, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
