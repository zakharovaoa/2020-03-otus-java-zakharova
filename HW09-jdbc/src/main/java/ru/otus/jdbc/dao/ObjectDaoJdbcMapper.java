package ru.otus.jdbc.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.ObjectDao;
import ru.otus.core.dao.ObjectDaoException;

import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.mapper.*;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ObjectDaoJdbcMapper<T> implements ObjectDao<T> {
    private static final Logger logger = LoggerFactory.getLogger(ObjectDaoJdbcMapper.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutorImpl<T> dbExecutor;
    private final JdbcMapperImpl<T> jdbcMapper;

    private EntityClassMetaData entityClassMetaData;
    private EntitySQLMetaData entitySQLMetaData;

    public ObjectDaoJdbcMapper(SessionManagerJdbc sessionManager, DbExecutorImpl<T> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
        jdbcMapper = new JdbcMapperImpl<T>(sessionManager, dbExecutor);
    }

    @Override
    public Optional<T> findById(long id) {
        /*try {
            return (Optional<T>) dbExecutor.executeSelect(getConnection(), "select id, name, age from user where id  = ?",
                    id, rs -> {
                        try {
                            if (rs.next()) {
                                return new User();
                                //rs.getLong("id"), rs.getString("name"), rs.getInt("age")
                            }
                        } catch (SQLException e) {
                            logger.error(e.getMessage(), e);
                        }
                        return null;
                    });
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }*/
        return Optional.empty();
    }

    @Override
    public long insertObject(T object) throws IllegalAccessException {
        jdbcMapper.insert(object);
        return jdbcMapper.getResultInsert();
    }


    @Override
    public void updateObject(T object) {

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
