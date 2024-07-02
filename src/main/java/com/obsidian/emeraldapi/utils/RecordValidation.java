package com.obsidian.emeraldapi.utils;

import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;

public class RecordValidation<T> {
    private final T record;
    
    public RecordValidation(T record) {
        this.record = record;
    }
    
    public ArrayList<Object> removeNullValues(Class<T> clazz, T obj)   {
        RecordComponent[] components = clazz.getRecordComponents();
        ArrayList<Field> fields = new ArrayList<>();
        for (var comp : components) {
            try {
                Field field = this.record.getClass()
                .getDeclaredField(comp.getName());
                field.setAccessible(true);
                fields.add(field);

            } catch (NoSuchFieldException e) { 
                
            }
        }

         ArrayList<Object> cleanedArray = new ArrayList<>();

        for (var i = 0; i < fields.size(); i++) {
            try {
                var element = fields.get(i);
                
                if(element.get(obj) == null) {
                    fields.remove(i);
                } else {
                    cleanedArray.add(element.get(obj));
                }
            } catch (IllegalAccessException e) {
            }
        }

        return cleanedArray;
    }  
}