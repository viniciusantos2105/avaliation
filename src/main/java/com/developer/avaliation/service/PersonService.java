package com.developer.avaliation.service;

import com.developer.avaliation.dto.AddressDto;
import com.developer.avaliation.dto.PersonDto;
import com.developer.avaliation.enums.Main;
import com.developer.avaliation.exceptions.IrregularDateException;
import com.developer.avaliation.exceptions.NotNullObjectException;
import com.developer.avaliation.exceptions.PersonNotFoundException;
import com.developer.avaliation.exceptions.ResourceExceptionHandler;
import com.developer.avaliation.model.Address;
import com.developer.avaliation.model.Person;
import com.developer.avaliation.repository.AddressRepository;
import com.developer.avaliation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private ResourceExceptionHandler resourceExceptionHandler;


    public Person create(Person pessoa) throws ParseException {
        if(pessoa == null) throw new NotNullObjectException();

        String regex = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pessoa.getDataNascimento());

        if(!matcher.find()){
            throw new IrregularDateException();
        }
        if(pessoa.getNome() == null || pessoa.getDataNascimento() == null){
            throw new NotNullObjectException();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return repository.save(pessoa);
    }

    public Person findById(Long id){
        return repository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public Person update(PersonDto personDto) throws ParseException {
        if(personDto == null) throw new NotNullObjectException();

        Person person = repository.findById(personDto.getId()).orElseThrow(PersonNotFoundException::new);

        if(personDto.getDataNascimento() != null && personDto.getNome() != null){
            person.setNome(personDto.getNome());
            person.setDataNascimento(personDto.getDataNascimento());
            return repository.save(person);
        }
        if(personDto.getNome() != null){
            person.setNome(personDto.getNome());
            return repository.save(person);
        }
        if(personDto.getDataNascimento() != null){
            person.setDataNascimento(personDto.getDataNascimento());
            return repository.save(person);
        }
        else{
            return null;
        }
    }

    public Person addAddress(AddressDto addressDto){
        if(addressDto == null) throw new NotNullObjectException();

        Person person = findById(addressDto.getId());
        Address address = createAddress(addressDto);

        if(address.getSelecaoEndereco().equals(Main.PRINCIPAL)){
            int position = person.getAddress().size();
            position--;
            for(int x = position; x >= 0; x--){
                if (person.getAddress().isEmpty()) {
                    person.getAddress().add(address);
                    return repository.save(person);
                } else if (person.getAddress().get(x).getSelecaoEndereco().equals(Main.PRINCIPAL)) {
                    person.getAddress().get(x).setSelecaoEndereco(Main.SECUNDARIO);
                }
            }
        }
        person.getAddress().add(address);
        return repository.save(person);
    }

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Address createAddress(AddressDto addressDto){
        Address address = new Address();
        address.setLogradouro(addressDto.getLogradouro());
        address.setCep(addressDto.getCep());
        address.setCidade(addressDto.getCidade());
        address.setNumero(addressDto.getNumero());
        if(addressDto.getSelecaoEndereco().equals(Main.PRINCIPAL.getDescricao())){
            address.setSelecaoEndereco(Main.PRINCIPAL);
            addressRepository.save(address);
        }
        else if(addressDto.getSelecaoEndereco().equals(Main.SECUNDARIO.getDescricao())){
            address.setSelecaoEndereco(Main.SECUNDARIO);
            addressRepository.save(address);
        }
        return address;
    }

    public void delete(Long id){
        Person person = repository.findById(id).orElseThrow(PersonNotFoundException::new);
        repository.delete(person);
    }

}
