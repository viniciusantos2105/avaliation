package com.developer.avaliation.mock;

import com.developer.avaliation.dto.AddressDto;
import com.developer.avaliation.dto.PersonDto;
import com.developer.avaliation.enums.Main;
import com.developer.avaliation.model.Address;
import com.developer.avaliation.model.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockPerson {

    public Person mockEntity() {
        return mockEntity(1);
    }

    public PersonDto mockEntityDto() {
        return mockEntityDto(1);
    }

    public List<Person> mockList() {
        List<Person> persons = new ArrayList<>();
        List<Address> addressList = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
            for(int x = 0; x < 14; x++){
                addressList.add(mockAddress(x));
            }
            persons.get(i).setAddress(addressList);
        }
        return persons;
    }
    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setNome("Person test" + number);
        person.setDataNascimento("12/12/2012");
        person.setAddress(mockAddressList(1));
        return person;
    }

    public PersonDto mockEntityDto(Integer number){
        PersonDto personDto = new PersonDto();
        personDto.setNome("Person test" + number);
        personDto.setDataNascimento("12/12/2012");
        return personDto;
    }

    public Address mockAddress(Integer number){
        Address address = new Address();
        address.setCep("cep" + number);
        address.setCidade("cidade" + number);
        address.setLogradouro("logradouro" + number);
        address.setNumero(number);
        address.setSelecaoEndereco(Main.PRINCIPAL);
        return address;
    }

    public AddressDto mockAddressDto(Integer number){
        AddressDto address = new AddressDto();
        address.setCep("cep" + number);
        address.setCidade("cidade" + number);
        address.setLogradouro("logradouro" + number);
        address.setNumero(number);
        address.setSelecaoEndereco("PRINCIPAL");
        return address;
    }

    public List<Address> mockAddressList(Integer number){
        List<Address> addressList = new ArrayList<>();
        Address address = new Address();
        address.setCep("cep" + number);
        address.setCidade("cidade" + number);
        address.setLogradouro("logradouro" + number);
        address.setNumero(number);
        address.setSelecaoEndereco(Main.SECUNDARIO);
        addressList.add(address);
        return addressList;
    }
}