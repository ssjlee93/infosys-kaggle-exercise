package com.example.infosyskaggleexercise.exceptions;

public class WrongDateException extends Exception {
    public WrongDateException(String date) {
        super("Input date " + date + " is out of range");
    }
}
