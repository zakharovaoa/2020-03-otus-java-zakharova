package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceObjectImpl;
import ru.otus.h2.DataSourceH2;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.dao.ObjectDaoJdbcMapper;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbServiceDemo {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) throws Exception {
        var dataSource = new DataSourceH2();
        var demo = new DbServiceDemo();

        demo.createTable(dataSource);

        var sessionManager = new SessionManagerJdbc(dataSource);
        DbExecutorImpl<User> dbExecutor = new DbExecutorImpl<>();
        var objectDao = new ObjectDaoJdbcMapper(sessionManager, dbExecutor);

        var dbServiceObject = new DbServiceObjectImpl(objectDao);
        User user = new User();
        user.setId(1);
        user.setName("Test1");
        user.setAge(46);

        var id = dbServiceObject.saveObject(user);
        System.out.println(id);

        User user2 = new User();
        user2.setId(1);
        user2.setName("Test1");
        user2.setAge(46);

        var id2 = dbServiceObject.saveObject(user2);
        System.out.println(id2);
        //var id = dbServiceUser.saveUser(new User(0, "dbServiceUser", 45));
       /* Optional<User> user = dbServiceUser.getUser(id);

        user.ifPresentOrElse(
                crUser -> logger.info("created user, name:{}", crUser.getName()),
                () -> logger.info("user was not created")
        );
*/
    }

    private void createTable(DataSource dataSource) throws SQLException {
        try (var connection = dataSource.getConnection();
             var pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50), age int(3))")) { //bigint(20) not null
            pst.executeUpdate();
        }
        System.out.println("table created");
    }

    private void createTableAccount(DataSource dataSource) throws SQLException {
        try (var connection = dataSource.getConnection();
             var pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50), age int(3))")) {
            pst.executeUpdate();
        }
        System.out.println("table created");
    }

}
