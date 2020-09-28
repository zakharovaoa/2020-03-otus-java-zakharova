package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.ObjectDaoException;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
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
        if (entityClassMetaData == null || !entityClassMetaData.isInit()) {
            entityClassMetaData = new EntityClassMetaDataImpl(objectData.getClass());
        }
        if (entitySQLMetaData == null || !entitySQLMetaData.isInit()) {
            entitySQLMetaData = new EntitySQLMetaDataImpl(objectData.getClass(), entityClassMetaData);
        }
        List<Field> list = new ArrayList<>(entityClassMetaData.getFieldsWithoutId());
        list.add(entityClassMetaData.getIdField());
        List<Object> listValue = getListValue(list, objectData);
        try {
             dbExecutor.executeUpdate(getConnection(), entitySQLMetaData.getUpdateSql(),
                     listValue);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ObjectDaoException(e);
        }
    }

    @Override
    public T findById(long id, Class<T> clazz) {
        if (entityClassMetaData == null || !entityClassMetaData.isInit()) {
            entityClassMetaData = new EntityClassMetaDataImpl(clazz);
        }
        if (entitySQLMetaData == null || !entitySQLMetaData.isInit()) {
            entitySQLMetaData = new EntitySQLMetaDataImpl(clazz, entityClassMetaData);
        }
        try {
            Optional<T> optionalObject = dbExecutor.executeSelect(getConnection(), entitySQLMetaData.getSelectByIdSql(),
                id, rs -> {
                try {
                    if (rs.next()) {
                        Constructor<T> constructor = entityClassMetaData.getConstructor();
                        T object = constructor.newInstance();
                        List<Field> listFieldsWithoutId = entityClassMetaData.getFieldsWithoutId();
                        for (Field field : listFieldsWithoutId) {
                            var columnLabel = field.getName();
                            var fieldType = field.getType();
                            if (String.class.equals(fieldType)) {
                                field.setAccessible(true);
                                field.set(object, rs.getString(columnLabel));
                            } else if (int.class.equals(fieldType) || Integer.class.equals(fieldType)) {
                                field.setAccessible(true);
                                field.set(object, rs.getInt(columnLabel));
                            } else if (long.class.equals(fieldType) || Long.class.equals(fieldType)) {
                                field.setAccessible(true);
                                field.set(object, rs.getLong(columnLabel));
                            }
                        }
                        var field = entityClassMetaData.getIdField();
                        field.setAccessible(true);
                        field.set(object, id);
                        return object;
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                return null;
                });
            return optionalObject.isPresent() ? optionalObject.get() : null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }


    @Override
    public void insertOrUpdate(T objectData) {
    }
}
