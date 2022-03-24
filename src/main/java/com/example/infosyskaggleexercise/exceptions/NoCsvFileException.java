package com.example.infosyskaggleexercise.exceptions;

public class NoCsvFileException extends Exception {
    public NoCsvFileException(String filename) {
        super("Could not find the file : " + filename);
    }
}
