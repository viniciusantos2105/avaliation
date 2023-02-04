package com.developer.avaliation.dto;

import jakarta.persistence.Entity;

import java.util.Date;

public class PersonDto {

    private Long id;

    private String nome;

    private String dataNascimento;

    public PersonDto(){}

    public PersonDto(Long id, String nome, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
