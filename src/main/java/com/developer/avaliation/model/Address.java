package com.developer.avaliation.model;

import com.developer.avaliation.enums.Main;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
    private Main selecaoEndereco;

    public Address(){}

    public Address(Long id, String logradouro, String cep, Integer numero, String cidade) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCep() {
        return cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Main getSelecaoEndereco() {
        return selecaoEndereco;
    }

    public void setSelecaoEndereco(Main selecaoEndereco) {
        this.selecaoEndereco = selecaoEndereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(logradouro, address.logradouro) && Objects.equals(cep, address.cep) && Objects.equals(numero, address.numero) && Objects.equals(cidade, address.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logradouro, cep, numero, cidade);
    }
}
