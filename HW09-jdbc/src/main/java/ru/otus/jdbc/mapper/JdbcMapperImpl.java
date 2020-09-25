package ru.otus.jdbc.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDaoException;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.dao.UserDaoJdbc;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcMapperImpl<T> implements JdbcMapper<T> {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoJdbc.class);

    private final SessionManagerJdbc sessionManager;
    private final DbExecutorImpl<User> dbExecutor;

    public JdbcMapperImpl(SessionManagerJdbc sessionManager, DbExecutorImpl<User> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public void insert(T objectData) {
        //insert into user(name, age) values (?, ?)
        getAllFields(T objectData)
        try {
            return dbExecutor.executeInsert(getConnection(), "insert into user(name, age) values (?, ?)",
                    user.getName(), user.getAge());
            //Collections.singletonList(user.getName(), user.getAge()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public void update(T objectData) {

    }

    @Override
    public void insertOrUpdate(T objectData) {

    }

    @Override
    public Object findById(long id, Class<T> clazz) {
        //getSelectByIdSql
        //return null;
        /*    try {
                return dbExecutor.executeSelect(getConnection(), "select id, name, age from user where id  = ?",
                        id, rs -> {
                            try {
                                if (rs.next()) {
                                    return new User(rs.getLong("id"), rs.getString("name"), rs.getInt("age"));
                                }
                            } catch (SQLException e) {
                                logger.error(e.getMessage(), e);
                            }
                            return null;
                        });
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return Optional.empty();           */

    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }

}
