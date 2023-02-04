package com.developer.avaliation.services;

import com.developer.avaliation.dto.AddressDto;
import com.developer.avaliation.dto.PersonDto;
import com.developer.avaliation.dto.UpdateAddressDto;
import com.developer.avaliation.enums.Main;
import com.developer.avaliation.exceptions.NotNullObjectException;
import com.developer.avaliation.exceptions.PersonNotFoundException;
import com.developer.avaliation.mock.MockAddress;
import com.developer.avaliation.mock.MockPerson;
import com.developer.avaliation.model.Address;
import com.developer.avaliation.model.Person;
import com.developer.avaliation.repository.AddressRepository;
import com.developer.avaliation.repository.PersonRepository;
import com.developer.avaliation.service.AddressService;
import com.developer.avaliation.service.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    MockAddress input;

    @InjectMocks
    private AddressService service;

    @Mock
    private AddressRepository repository;

    @Mock
    private PersonRepository personRepository;

    @BeforeAll()
    void setUpMocks() throws Exception{
        input = new MockAddress();
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testFindAll() {
        Person person = input.mockList();
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        var addressList = service.addressList(1L);

        assertNotNull(addressList);
        assertEquals(14, addressList.size());

        var addressOne = addressList.get(1);

        assertNotNull(addressOne);
        assertNotNull(addressOne.getLogradouro());
        assertNotNull(addressOne.getCep());
        assertNotNull(addressOne.getNumero());
        assertNotNull(addressOne.getCidade());
        assertNotNull(addressOne.getSelecaoEndereco());

        assertEquals("cep1", addressOne.getCep());
        assertEquals("cidade1", addressOne.getCidade());
        assertEquals("logradouro1", addressOne.getLogradouro());
        assertEquals(1, addressOne.getNumero());

        var addressFour = addressList.get(4);

        assertNotNull(addressFour);
        assertNotNull(addressFour.getLogradouro());
        assertNotNull(addressFour.getCep());
        assertNotNull(addressFour.getNumero());
        assertNotNull(addressFour.getCidade());
        assertNotNull(addressFour.getSelecaoEndereco());

        assertEquals("cep4", addressFour.getCep());
        assertEquals("cidade4", addressFour.getCidade());
        assertEquals("logradouro4", addressFour.getLogradouro());
        assertEquals(4, addressFour.getNumero());

        var addressSeven = addressList.get(7);

        assertNotNull(addressSeven);
        assertNotNull(addressSeven.getLogradouro());
        assertNotNull(addressSeven.getCep());
        assertNotNull(addressSeven.getNumero());
        assertNotNull(addressSeven.getCidade());
        assertNotNull(addressSeven.getSelecaoEndereco());

        assertEquals("cep7", addressSeven.getCep());
        assertEquals("cidade7", addressSeven.getCidade());
        assertEquals("logradouro7", addressSeven.getLogradouro());
        assertEquals(7, addressSeven.getNumero());
    }

    @Test
    void testFindAllWithNullPerson() {
        Exception exception = assertThrows(PersonNotFoundException.class, () -> {
            service.addressList(null);
        });

        String expectedMessage = "Pessoa não encontrada!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateAddress(){
        UpdateAddressDto updateAddressDto = new UpdateAddressDto();
        updateAddressDto.setIdAddress(1L);
        updateAddressDto.setIdPerson(1L);

        Person person = input.mockPerson(1);
        person.getAddress().get(0).setId(1L);

        Person persisted = input.mockPerson(1);

        Address address = input.mockAddress(1);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(persisted);
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(address));

        var result = service.updateAddress(updateAddressDto);

        assertNotNull(result);
        assertNotNull(result.getLogradouro());
        assertNotNull(result.getCep());
        assertNotNull(result.getNumero());
        assertNotNull(result.getCidade());
        assertNotNull(result.getSelecaoEndereco());

        assertEquals("cep1", result.getCep());
        assertEquals("cidade1", result.getCidade());
        assertEquals("logradouro1", result.getLogradouro());
        assertEquals(1, result.getNumero());
    }


}
