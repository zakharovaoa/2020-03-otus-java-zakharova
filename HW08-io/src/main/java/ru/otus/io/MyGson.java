package ru.otus.io;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;
import java.util.Collection;

public class MyGson {

    public String toJson(AnyObject obj) throws Exception {
        Class<AnyObject> clazz = AnyObject.class;
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        Field[] fieldsAll = clazz.getDeclaredFields();
        for (Field field : fieldsAll) {
            field.setAccessible(true);
            jsonObjectBuilder = checkAddPrimitive(jsonObjectBuilder, field.getName(), field.getType(), field.get(obj));
            jsonObjectBuilder = checkAddArray(jsonObjectBuilder, field.getName(), field.getType(), field.get(obj));
            jsonObjectBuilder = checkAddCollection(jsonObjectBuilder, field.getName(), field.getType(), field.get(obj));
        }
        JsonObject jsonObject = jsonObjectBuilder.build();
        return jsonObject.toString();
    }

    public JsonObjectBuilder addValue(JsonObjectBuilder jsonObjectBuilder, String name, Class fieldType, Object value) {
        if (int.class.equals(fieldType) || Integer.class.equals(fieldType)) {
            jsonObjectBuilder.add(name, (int) value);
        } else if (String.class.equals(fieldType)) {
            jsonObjectBuilder.add(name, (String) value);
        } else if (long.class.equals(fieldType) || Long.class.equals(fieldType)) {
            jsonObjectBuilder.add(name, (long) value);
        } else if (double.class.equals(fieldType) || Double.class.equals(fieldType)) {
            jsonObjectBuilder.add(name, (double) value);
        } else if (char.class.equals(fieldType)) {
            jsonObjectBuilder.add(name, String.valueOf(value));
        } else if (boolean.class.equals(fieldType)) {
            jsonObjectBuilder.add(name, (boolean) value);
        }
        return jsonObjectBuilder;
    }

    public JsonObjectBuilder checkAddPrimitive(JsonObjectBuilder jsonObjectBuilder, String fieldName,
                                               Class fieldType, Object fieldValue) {
        if (fieldType.isPrimitive()) {
            jsonObjectBuilder = addValue(jsonObjectBuilder, fieldName, fieldType, fieldValue);
        }
        return jsonObjectBuilder;
    }

    public JsonObjectBuilder checkAddArray(JsonObjectBuilder jsonObjectBuilder, String fieldName,
                                           Class fieldType, Object fieldValue) {
        if (fieldType.isArray() &&
                fieldValue != null) {
            if (fieldType.getComponentType().isPrimitive()) {
                JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
                int[] array = (int[]) fieldValue;
                for (int element : array) {
                    jsonArrayBuilder.add(element);
                }
                jsonObjectBuilder.add(fieldName, jsonArrayBuilder);
            }
        }
        return jsonObjectBuilder;
    }

    public JsonObjectBuilder checkAddCollection(JsonObjectBuilder jsonObjectBuilder, String fieldName,
                                                Class fieldType, Object fieldValue) {
        if (Collection.class.isAssignableFrom(fieldType) &&
                fieldValue != null) {
            JsonArrayBuilder jsonArrayBuilderList = Json.createArrayBuilder();
            Collection<String> list = (Collection<String>) fieldValue;
            for (String string : list) {
                jsonArrayBuilderList.add(string);
            }
            jsonObjectBuilder.add(fieldName,
                    jsonArrayBuilderList);
        }
        return jsonObjectBuilder;
    }
}