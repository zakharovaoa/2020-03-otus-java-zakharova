package ru.otus.core.service;

import ru.otus.core.model.User;

import java.util.Optional;

public interface DBServiceObject<T> {

    long saveObject(T object);

    Optional<T> getObject(long id);

    //void updateObject(T object);
    //void insertOrUpdate(T object);
}
