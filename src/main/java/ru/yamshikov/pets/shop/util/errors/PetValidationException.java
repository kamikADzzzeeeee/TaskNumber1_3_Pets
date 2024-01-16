package ru.yamshikov.pets.shop.util.errors;

import lombok.Getter;

import java.util.List;

@Getter
public class PetValidationException extends RuntimeException{
    List<String> errors;
    public PetValidationException(List<String> errors) {
        super();
        this.errors = errors;
    }
}
