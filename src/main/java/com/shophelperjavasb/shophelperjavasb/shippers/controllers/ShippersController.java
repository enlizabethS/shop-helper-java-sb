package com.shophelperjavasb.shophelperjavasb.shippers.controllers;

import com.shophelperjavasb.shophelperjavasb.shippers.dto.ShippersDTO;
import com.shophelperjavasb.shophelperjavasb.shippers.services.ShippersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShippersController {
    private final ShippersService shipperService;

    @GetMapping("/{id}")
    public ResponseEntity<ShippersDTO> getShipperById(@PathVariable Long id) {
        ShippersDTO shipperDTO = shipperService.getShipperById(id);
        return new ResponseEntity<>(shipperDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShippersDTO> createShipper(@RequestBody ShippersDTO shipperDTO) {
        ShippersDTO createdShipper = shipperService.createShipper(shipperDTO);
        return new ResponseEntity<>(createdShipper, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ShippersDTO> updateShipper(@PathVariable Long id, @RequestBody ShippersDTO shipperDTO) {
        ShippersDTO updatedShipper = shipperService.updateShipper(id, shipperDTO);
        return new ResponseEntity<>(updatedShipper, HttpStatus.OK);
    }
}
