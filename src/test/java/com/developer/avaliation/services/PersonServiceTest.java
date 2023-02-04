package com.developer.avaliation.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.developer.avaliation.dto.AddressDto;
import com.developer.avaliation.dto.PersonDto;
import com.developer.avaliation.enums.Main;
import com.developer.avaliation.exceptions.NotNullObjectException;
import com.developer.avaliation.mock.MockPerson;
import com.developer.avaliation.model.Address;
import com.developer.avaliation.model.Person;
import com.developer.avaliation.repository.AddressRepository;
import com.developer.avaliation.repository.PersonRepository;
import com.developer.avaliation.service.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @Mock
    private AddressRepository addressRepository;

    @BeforeAll()
    void setUpMocks() throws Exception{
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById(){
        Person entity = input.mockEntity();
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getNome());
        assertNotNull(result.getDataNascimento());
        assertNotNull(result.getAddress());

        assertEquals("Person test1", result.getNome());
        assertEquals("cep1", result.getAddress().get(0).getCep());
        assertEquals("cidade1", result.getAddress().get(0).getCidade());
        assertEquals("logradouro1", result.getAddress().get(0).getLogradouro());
        assertEquals(1, result.getAddress().get(0).getNumero());
    }

    @Test
    void testCreate() throws ParseException {
        Person entity = input.mockEntity();
        entity.setId(1L);

        Person persisted = entity;
        persisted.setId(1L);

        Person pessoa = input.mockEntity();
        pessoa.setId(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(pessoa);

        assertNotNull(result);
        assertNotNull(result.getNome());
        assertNotNull(result.getDataNascimento());
        assertNotNull(result.getAddress());

        assertEquals("Person test1", result.getNome());
        assertEquals("12/12/2012", result.getDataNascimento());
        assertEquals("cep1", result.getAddress().get(0).getCep());
        assertEquals("cidade1", result.getAddress().get(0).getCidade());
        assertEquals("logradouro1", result.getAddress().get(0).getLogradouro());
        assertEquals(1, result.getAddress().get(0).getNumero());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(NotNullObjectException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "Preencha todos os campos!!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate() throws ParseException {
        Person entity = input.mockEntity();

        Person persisted = entity;
        persisted.setId(1L);

        PersonDto personDto = input.mockEntityDto();
        personDto.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(personDto);

        assertNotNull(result);
        assertNotNull(result.getNome());
        assertNotNull(result.getDataNascimento());
        assertNotNull(result.getAddress());

        assertEquals("Person test1", result.getNome());
        assertEquals("12/12/2012", result.getDataNascimento());
        assertEquals("cep1", result.getAddress().get(0).getCep());
        assertEquals("cidade1", result.getAddress().get(0).getCidade());
        assertEquals("logradouro1", result.getAddress().get(0).getLogradouro());
        assertEquals(1, result.getAddress().get(0).getNumero());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(NotNullObjectException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "Preencha todos os campos!!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDelete(){
        Person entity = input.mockEntity();
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }

    @Test
    void testAddAddress(){
        Person entity = input.mockEntity();

        Person persisted = entity;
        persisted.setId(1L);

        AddressDto addressDto = input.mockAddressDto(1);
        addressDto.setId(1L);

        Address address = input.mockAddress(1);
        address.setId(1L);

        Address addressPersisted = address;
        addressPersisted.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(addressRepository.save(address)).thenReturn(addressPersisted);
        when(repository.save(entity)).thenReturn(persisted);

        var person = service.addAddress(addressDto);

        assertNotNull(person);

    }

    @Test
    void testFindAll() {
        List<Person> list = input.mockList();

        when(repository.findAll()).thenReturn(list);

        var people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getNome());
        assertNotNull(personOne.getDataNascimento());
        assertNotNull(personOne.getAddress());

        assertEquals("Person test1", personOne.getNome());
        assertEquals("12/12/2012", personOne.getDataNascimento());
        assertEquals("cep0", personOne.getAddress().get(0).getCep());
        assertEquals("cidade0", personOne.getAddress().get(0).getCidade());
        assertEquals("logradouro0", personOne.getAddress().get(0).getLogradouro());
        assertEquals(0, personOne.getAddress().get(0).getNumero());
        assertEquals(Main.PRINCIPAL, personOne.getAddress().get(0).getSelecaoEndereco());

        var personFour = people.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getNome());
        assertNotNull(personFour.getDataNascimento());
        assertNotNull(personFour.getAddress());

        assertEquals("Person test4", personFour.getNome());
        assertEquals("12/12/2012", personFour.getDataNascimento());
        assertEquals("cep3", personFour.getAddress().get(3).getCep());
        assertEquals("cidade3", personFour.getAddress().get(3).getCidade());
        assertEquals("logradouro3", personFour.getAddress().get(3).getLogradouro());
        assertEquals(3, personFour.getAddress().get(3).getNumero());
        assertEquals(Main.PRINCIPAL, personFour.getAddress().get(3).getSelecaoEndereco());

        var personSeven = people.get(7);

        assertNotNull(personSeven);
        assertNotNull(personSeven.getNome());
        assertNotNull(personSeven.getDataNascimento());
        assertNotNull(personSeven.getAddress());

        assertEquals("Person test7", personSeven.getNome());
        assertEquals("12/12/2012", personSeven.getDataNascimento());
        assertEquals("cep6", personSeven.getAddress().get(6).getCep());
        assertEquals("cidade6", personSeven.getAddress().get(6).getCidade());
        assertEquals("logradouro6", personSeven.getAddress().get(6).getLogradouro());
        assertEquals(6, personSeven.getAddress().get(6).getNumero());
        assertEquals(Main.PRINCIPAL, personSeven.getAddress().get(6).getSelecaoEndereco());
    }
}
