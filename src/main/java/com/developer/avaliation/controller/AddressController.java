package com.developer.avaliation.controller;

import com.developer.avaliation.dto.UpdateAddressDto;
import com.developer.avaliation.model.Address;
import com.developer.avaliation.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping("/list/{id}")
    private List<Address> addressList(@PathVariable Long id){
        return service.addressList(id);
    }

    @PutMapping("/update")
    private Address update(@RequestBody UpdateAddressDto updateAddressDto){
        return service.updateAddress(updateAddressDto);
    }
}
