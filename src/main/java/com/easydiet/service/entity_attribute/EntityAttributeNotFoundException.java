package com.easydiet.service.entity_attribute;

public class EntityAttributeNotFoundException extends Exception{
    public EntityAttributeNotFoundException(String id) { super ("Атрибуты сущности " + id + " не найдены");}
}
