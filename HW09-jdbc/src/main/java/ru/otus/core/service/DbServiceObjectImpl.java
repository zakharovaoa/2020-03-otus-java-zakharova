package ru.otus.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.ObjectDao;

import java.util.Optional;

public class DbServiceObjectImpl<T> implements DBServiceObject<T> {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceObjectImpl.class);

    private final ObjectDao<T> objectDao;

    public DbServiceObjectImpl(ObjectDao<T> objectDao) {
        this.objectDao = objectDao;
    }

    @Override
    public long saveObject(T object) {
        try (var sessionManager = objectDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                var fieldId = objectDao.insertObject(object);
                sessionManager.commitSession();

                logger.info("created object: {}", fieldId);
                return fieldId;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<T> getObject(long id) {
        try (var sessionManager = objectDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<T> userOptional = objectDao.findById(id);
                logger.info("object: {}", userOptional.orElse(null));
                return userOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

    @Override
    public void updateObject(T object) {
        try (var sessionManager = objectDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                objectDao.updateObject(object);
                sessionManager.commitSession();
                logger.info("update object");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public void insertOrUpdate(T object) {
    }
}
