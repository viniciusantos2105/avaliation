package com.developer.avaliation.controller;

import com.developer.avaliation.dto.AddressDto;
import com.developer.avaliation.dto.PersonDto;
import com.developer.avaliation.model.Person;
import com.developer.avaliation.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id){
       return service.findById(id);
    }

    @GetMapping("/findAll")
    public List<Person> findAll(){
        return service.findAll();
    }

    @PostMapping("/create")
    public Person create(@RequestBody Person person) throws ParseException {
        return service.create(person);
    }

    @PutMapping("/update")
    public Person update(@RequestBody PersonDto personDto) throws ParseException {
        return service.update(personDto);
    }

    @PostMapping("/addAddress")
    public Person addAddress(@RequestBody AddressDto addressDto){
        return service.addAddress(addressDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
