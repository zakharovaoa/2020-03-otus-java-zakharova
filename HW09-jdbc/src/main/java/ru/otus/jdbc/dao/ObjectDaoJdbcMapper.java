package ru.otus.jdbc.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.ObjectDao;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.EntitySQLMetaData;
import ru.otus.jdbc.mapper.JdbcMapperImpl;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.Connection;
import java.util.Objects;
import java.util.Optional;

public class ObjectDaoJdbcMapper<T> implements ObjectDao<T> {
    private static final Logger logger = LoggerFactory.getLogger(ObjectDaoJdbcMapper.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutorImpl<T> dbExecutor;
    private final JdbcMapperImpl<T> jdbcMapper;
    Class<T> clazz;

    private EntityClassMetaData entityClassMetaData;
    private EntitySQLMetaData entitySQLMetaData;

    public ObjectDaoJdbcMapper(SessionManagerJdbc sessionManager, DbExecutorImpl<T> dbExecutor, Class<T> clazz) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        this.clazz = clazz;
        jdbcMapper = new JdbcMapperImpl<T>(sessionManager, dbExecutor);
    }

    @Override
    public Optional<T> findById(long id) {
        T object =  jdbcMapper.findById(id, clazz);
        if (Objects.nonNull(object)) {
            return Optional.of(object);
        }
        return Optional.empty();
    }

    @Override
    public long insertObject(T object) {
        jdbcMapper.insert(object);
        return jdbcMapper.getResultInsert();
    }

    @Override
    public void updateObject(T object) {
        jdbcMapper.update(object);
    }

    @Override
    public void insertOrUpdate(T object) {
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }
}
