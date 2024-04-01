package com.easydiet.service;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException() {

        super("Object is not found.");
    }
}
