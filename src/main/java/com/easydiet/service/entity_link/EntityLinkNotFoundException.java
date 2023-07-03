package com.easydiet.service.entity_link;

public class EntityLinkNotFoundException extends Exception {
    public EntityLinkNotFoundException(String id) {
        super("Связь сущностей " + id + " не найдена.");
    }
}
