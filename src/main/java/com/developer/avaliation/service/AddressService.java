package com.developer.avaliation.service;

import com.developer.avaliation.dto.AddressDto;
import com.developer.avaliation.dto.UpdateAddressDto;
import com.developer.avaliation.enums.Main;
import com.developer.avaliation.exceptions.EmptyAddressException;
import com.developer.avaliation.exceptions.PersonNotFoundException;
import com.developer.avaliation.model.Address;
import com.developer.avaliation.model.Person;
import com.developer.avaliation.repository.AddressRepository;
import com.developer.avaliation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private PersonRepository personRepository;


    public List<Address> addressList(Long id){
        Person person = personRepository.findById(id).orElseThrow((PersonNotFoundException::new));
        return person.getAddress();
    }

    public Address updateAddress(UpdateAddressDto updateAddressDto){
        Person person = personRepository.findById(updateAddressDto.getIdPerson()).orElseThrow((PersonNotFoundException::new));
        List<Address> addressList = person.getAddress();
        int position = addressList.size();
        position --;
        for(int x = position; x > -1; x--){
            if (person.getAddress().isEmpty()) {
                throw new EmptyAddressException();
            }
            if (!Objects.equals(person.getAddress().get(x).getId(), updateAddressDto.getIdAddress())) {
                person.getAddress().get(x).setSelecaoEndereco(Main.SECUNDARIO);
                personRepository.save(person);
            }
            else if(person.getAddress().get(x).getId().equals(updateAddressDto.getIdAddress())){
                person.getAddress().get(x).setSelecaoEndereco(Main.PRINCIPAL);
                Address address = person.getAddress().get(x);
                personRepository.save(person);
            }
        }
        return repository.findById(updateAddressDto.getIdAddress()).orElseThrow(EmptyAddressException::new);
    }
}
