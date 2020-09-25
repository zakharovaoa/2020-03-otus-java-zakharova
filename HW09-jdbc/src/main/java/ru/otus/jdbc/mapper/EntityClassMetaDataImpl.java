package ru.otus.jdbc.mapper;

import ru.otus.Id;
import ru.otus.core.model.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private T objectData;

    public EntityClassMetaDataImpl(T objectData) {
        this.objectData = objectData;
    }

    @Override
    public String getName() {
        var clazz = objectData.getClass();
        return clazz.getSimpleName();
    }

    @Override
    public Constructor getConstructor() {
        try {
            var clazz = objectData.getClass();
            //Class<T> clazz

            //Class<T> clazz = (Class<T>) objectData;
            //Constructor =

            var constructor = clazz.getConstructor(long.class, String.class, int.class);
            Field[] fieldsAll = clazz.getDeclaredFields();

           // Class<?> clazz = T;
           // JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
           // Field[] fieldsAll = clazz.getDeclaredFields();
            return constructor;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Field getIdField() {
        var listAll = getAllFields();
        Field fieldId = null;
        for (Field field : listAll) {
            if (field.isAnnotationPresent(Id.class)) {
                fieldId = field;
                break;
            }
        }
        return fieldId;
    }

    @Override
    public List<Field> getAllFields() {
        var clazz = objectData.getClass();
        Field[] fieldsAll = clazz.getDeclaredFields();
        List<Field> list = new ArrayList<>(Arrays.asList(fieldsAll));
        return list;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        var listAll = getAllFields();
        List<Field> listWithoutId = new ArrayList<>();
        for (Field field : listAll) {
          if (!field.isAnnotationPresent(Id.class)) {
              listWithoutId.add(field);
            }
        }
        return listWithoutId;
    }
}
