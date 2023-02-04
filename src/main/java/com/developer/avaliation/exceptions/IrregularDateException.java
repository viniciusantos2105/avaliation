package com.developer.avaliation.exceptions;

public class IrregularDateException extends RuntimeException{

    public IrregularDateException() {
        super("Data inválida");
    }
}
