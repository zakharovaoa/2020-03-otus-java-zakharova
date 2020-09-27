package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import ru.otus.core.dao.UserDaoException;
import ru.otus.core.dao.ObjectDaoException;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.dao.ObjectDaoJdbcMapper;
import ru.otus.jdbc.dao.UserDaoJdbc;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMapperImpl<T> implements JdbcMapper<T> {

    private static final Logger logger = LoggerFactory.getLogger(JdbcMapperImpl.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutorImpl<T> dbExecutor;
    private EntityClassMetaData entityClassMetaData;
    private EntitySQLMetaData entitySQLMetaData;
    private long resultInsert;

    public JdbcMapperImpl(SessionManagerJdbc sessionManager, DbExecutorImpl<T> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public void insert(T objectData) {
        if (entityClassMetaData == null || !entityClassMetaData.isInit()) {
            entityClassMetaData = new EntityClassMetaDataImpl(objectData.getClass());
        }
        if (entitySQLMetaData == null || !entitySQLMetaData.isInit()) {
            entitySQLMetaData = new EntitySQLMetaDataImpl(objectData.getClass(), entityClassMetaData);
        }
        List<Field> listWithoutId = entityClassMetaData.getFieldsWithoutId();
        List<Object> listValueWithoutId = getListValue(listWithoutId, objectData);
        try {
            resultInsert = dbExecutor.executeInsert(getConnection(), entitySQLMetaData.getInsertSql(),
                    listValueWithoutId);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new ObjectDaoException(e);
            }
    }

    public long getResultInsert() {
        return resultInsert;
    }

    public List<Object> getListValue(List<Field> list, T objectData) {
        List<Object> listValue = new ArrayList<>();
        try {
                for (Field field : list) {
                    field.setAccessible(true);
                    System.out.println(field.get(objectData));
                    listValue.add(field.get(objectData));
                }
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
            throw new ObjectDaoException(e);
        }
        return listValue;
    }

    @Override
    public void update(T objectData) {

    }

    @Override
    public void insertOrUpdate(T objectData) {

    }

    @Override
    public T findById(long id, Class<T> clazz) {
        /*List<Field> list = getAllFields();
            try {
                return dbExecutor.executeSelect(getConnection(), getSelectByIdSql,
                        id, rs -> {
                            try {
                                if (rs.next()) {

                                    return new User(rs.getLong("id"), rs.getString("name"), rs.getInt("age"));
                                    Constructor<T> constructor = getConstructor;
                                    T object = constructor.newInstance(rs.getLong(list.get(0).getName()), rs.getString(list.get(1).getName()), rs.getInt(list.get(2).getName()));

                                }
                            } catch (SQLException e) {
                                logger.error(e.getMessage(), e);
                            }
                            return null;
                        });
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }*/
            return null;

    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

}
