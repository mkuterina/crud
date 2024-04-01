package com.easydiet.service;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException(String id) {

        super("Object with id = " +id + " is not found.");
    }
}
