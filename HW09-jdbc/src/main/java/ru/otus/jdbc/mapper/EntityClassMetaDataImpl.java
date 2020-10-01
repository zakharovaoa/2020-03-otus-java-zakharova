package ru.otus.jdbc.mapper;

import ru.otus.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private final Class<?> clazz;
    private final List<Field> listAll;
    private final List<Field> listWithoutId;
    private final String name;
    private final Constructor constructor;
    private final Field idField;
    private final boolean isInit;

    public EntityClassMetaDataImpl(Class<?> clazz) {
        isInit = true;
        this.clazz = clazz;
        listAll = getInitAllFields();
        listWithoutId = getInitListWithoutId();
        name = getValueName();
        constructor = getInitConstructor();
        idField = getInitIdField();
    }

    public boolean isInit() {
        return isInit;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getValueName() {
        return clazz.getSimpleName();
    }

    @Override
    public Constructor getConstructor() {
        return constructor;
    }

    public Constructor getInitConstructor() {
        try {
            var constructor = clazz.getConstructor();
            return constructor;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    public Field getInitIdField() {
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
        return listAll;
    }

    public List<Field> getInitAllFields() {
        Field[] fieldsAll = clazz.getDeclaredFields();
        List<Field> list = new ArrayList<>(Arrays.asList(fieldsAll));
        return list;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return listWithoutId;
    }

    public List<Field> getInitListWithoutId() {
        List<Field> listWithoutId = new ArrayList<>();
        for (Field field : listAll) {
            if (!field.isAnnotationPresent(Id.class)) {
                listWithoutId.add(field);
            }
        }
        return listWithoutId;
    }
}
