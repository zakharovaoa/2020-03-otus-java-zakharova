package ru.otus.jdbc.mapper;

public interface JdbcMapper<T> {
    void insert(T objectData) throws IllegalAccessException;

    void update(T objectData);

    void insertOrUpdate(T objectData);

    T findById(long id, Class<T> clazz);
}
