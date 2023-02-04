package com.developer.avaliation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public enum Main {

    PRINCIPAL(1, "PRINCIPAL"),
    SECUNDARIO(2, "SECUNDARIO");

    Integer numero;
    String descricao;

    Main(Integer numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }
}
