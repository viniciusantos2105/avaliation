package com.developer.avaliation.exceptions;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException() {
        super("Pessoa não encontrada!");
    }
}
