package com.developer.avaliation.dto;

public class AddressDto {

    private Long id;
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;

    private String selecaoEndereco;

    public AddressDto(){}

    public AddressDto(Long id, String logradouro, String cep, Integer numero, String cidade, String selecaoEndereco) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.selecaoEndereco = selecaoEndereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSelecaoEndereco() {
        return selecaoEndereco;
    }

    public void setSelecaoEndereco(String selecaoEndereco) {
        this.selecaoEndereco = selecaoEndereco;
    }
}
