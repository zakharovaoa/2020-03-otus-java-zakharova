package ru.otus.core.dao;

import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface ObjectDao<T> {
    Optional<T> findById(long id);

    long insertObject(T object) throws IllegalAccessException;

    void updateObject(T object);

    void insertOrUpdate(T object);

    SessionManager getSessionManager();
}
