package com.developer.avaliation.mock;

import com.developer.avaliation.dto.AddressDto;
import com.developer.avaliation.dto.PersonDto;
import com.developer.avaliation.enums.Main;
import com.developer.avaliation.model.Address;
import com.developer.avaliation.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockAddress {

    public Address mockEntity() {
        return mockAddress(1);
    }

    public AddressDto mockEntityDto() {
        return mockAddressDto(1);
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

    public List<Address> mockList(Integer number){
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

    public Person mockPerson(Integer number) {
        Person person = new Person();
        person.setNome("Person test" + number);
        person.setDataNascimento("12/12/2012");
        person.setAddress(mockAddressList(1));
        return person;
    }

    public Person mockList() {
        Person person = new Person();
        person = mockPerson(1);
        List<Address> addressList = new ArrayList<>();
        for(int x = 0; x < 14; x++){
            addressList.add(mockAddress(x));
        }
        person.setAddress(addressList);
        return person;
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
