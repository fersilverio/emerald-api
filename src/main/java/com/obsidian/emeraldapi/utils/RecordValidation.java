package com.obsidian.emeraldapi.utils;

import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecordValidation<T> {
    private final T record;

    public RecordValidation(T record) {
        this.record = record;
    }

    public ArrayList<String> getFieldList(Class<T> clazz) {
        RecordComponent[] components = clazz.getRecordComponents();
        ArrayList<String> fields = new ArrayList<>();
        for (var comp : components) {
            try {
                Field field = this.record.getClass()
                .getDeclaredField(comp.getName());
                field.setAccessible(true);
                fields.add(field.getName());

            } catch (NoSuchFieldException e) { 
                
            }
        }

        return fields;
    }
    
    public Map<String, String> removeNullValues(Class<T> clazz, T obj)   {
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

        Map<String, String> cleanedMap = new HashMap<>();

        for (var i = 0; i < fields.size(); i++) {
            try {
                var element = fields.get(i);
                
                if (element.get(obj) != null) {
                    cleanedMap.put(element.getName(), element.get(obj).toString());
                }             
            } catch (IllegalAccessException e) {}
        }

        return cleanedMap;
    }  
}