package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.model.Account;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceObjectImpl;
import ru.otus.h2.DataSourceH2;
import ru.otus.jdbc.DbExecutorImpl;
import ru.otus.jdbc.dao.ObjectDaoJdbcMapper;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbServiceDemo {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) throws Exception {
        var dataSource = new DataSourceH2();
        var demo = new DbServiceDemo();
        demo.createTableUser(dataSource);
        demo.createTableAccount(dataSource);

        var sessionManager = new SessionManagerJdbc(dataSource);
        DbExecutorImpl<User> dbExecutor = new DbExecutorImpl<>();

        var objectDao = new ObjectDaoJdbcMapper<User>(sessionManager, dbExecutor,User.class);
        var dbServiceObject = new DbServiceObjectImpl(objectDao);

        User user = createUser(0, "Лукин Е.", 39);
        var id = dbServiceObject.saveObject(user);
        User user2 = createUser(0, "Громыко О.", 42);
        var id2 = dbServiceObject.saveObject(user2);
        Optional<User> userCreated = dbServiceObject.getObject(id);
        userCreated.ifPresentOrElse(
                crUser -> logger.info("get created user, name:{}, age:{}", crUser.getName(), crUser.getAge()),
                () -> logger.info("user was not created")
        );
        User userChange = createUser(1, "Филатов Л.", 45);
        dbServiceObject.updateObject(userChange);
        Optional<User> userAfterUpdate = dbServiceObject.getObject(id);
        userAfterUpdate.ifPresentOrElse(
                crUser -> logger.info("get updated user, name:{}, age:{}", crUser.getName(), crUser.getAge()),
                () -> logger.info("user was not updated")
        );

        var objectDao2 = new ObjectDaoJdbcMapper(sessionManager, dbExecutor, Account.class);
        var dbServiceObject2 = new DbServiceObjectImpl(objectDao2);

        Account account = createAccount(0,"Дебетовый", 12);
        var id3 = dbServiceObject2.saveObject(account);
        Optional<Account> accountCreated = dbServiceObject2.getObject(id3);
        accountCreated.ifPresentOrElse(
                crAccount -> logger.info("created account, type:{}, rest:{}", crAccount.getType(), crAccount.getRest()),
                () -> logger.info("account was not created")
        );
        Account accountChange = accountCreated.isPresent() ? accountCreated.get() : null;
        accountChange.setType("Кредитный");
        accountChange.setRest(15);
        dbServiceObject2.updateObject(accountChange);
        Optional<Account> accountAfterUpdate = dbServiceObject2.getObject(id3);
        accountAfterUpdate.ifPresentOrElse(
                crAccount -> logger.info("get updated account, type:{}, rest:{}", crAccount.getType(), crAccount.getRest()),
                () -> logger.info("account was not get")
        );
    }

    private static User createUser (long id, String name, int age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    private static Account createAccount (long no, String type, int rest) {
        Account account = new Account();
        account.setNo(no);
        account.setType(type);
        account.setRest(rest);
        return account;
    }

    private void createTableUser(DataSource dataSource) throws SQLException {
        try (var connection = dataSource.getConnection();
             var pst = connection.prepareStatement("create table user(id bigint(20) auto_increment, name varchar(255), age int(3))")) { //bigint(20) not null
            pst.executeUpdate();
        }
        System.out.println("table User created");
    }

    private void createTableAccount(DataSource dataSource) throws SQLException {
        try (var connection = dataSource.getConnection();
             var pst = connection.prepareStatement("create table account(no bigint(20) auto_increment, type varchar(255), rest number)")) {
            pst.executeUpdate();
        }
        System.out.println("table Account created");
    }
}
