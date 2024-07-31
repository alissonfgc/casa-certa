package com.alissonfgc.casacerta.services.exceptions;

public class UniqueException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String field;

    public UniqueException(String string) {
        super(string);
    }

    public UniqueException(String mesage, String field) {
        super(mesage);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
