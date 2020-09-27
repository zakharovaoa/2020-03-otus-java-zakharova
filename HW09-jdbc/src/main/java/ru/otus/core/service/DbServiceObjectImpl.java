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
                var userId = objectDao.insertObject(object);
                sessionManager.commitSession();

                logger.info("created user: {}", userId);
                return userId;
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

                logger.info("user: {}", userOptional.orElse(null));
                return userOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }


}
