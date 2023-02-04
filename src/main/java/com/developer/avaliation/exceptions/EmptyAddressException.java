package com.developer.avaliation.exceptions;

public class EmptyAddressException extends RuntimeException{

    public EmptyAddressException() {
        super("Nenhum endereço encontrado");
    }
}
