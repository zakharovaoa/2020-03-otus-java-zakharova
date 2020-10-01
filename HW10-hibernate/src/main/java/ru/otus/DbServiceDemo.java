package ru.otus;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbServiceDemo {
    private static Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);

        AddressDataSet addressDataSet = new AddressDataSet("Владивосток, ул. Приморская, 11");

        List<PhoneDataSet> list = new ArrayList<>();
        PhoneDataSet phoneDataSet1 = new PhoneDataSet("76237642364");
        PhoneDataSet phoneDataSet2 = new PhoneDataSet("12323213233");
        list.add(phoneDataSet1);
        list.add(phoneDataSet2);
        User user1 = new User("Вася", addressDataSet, list);
        System.out.println(user1);

        long id = dbServiceUser.saveUser(user1);
        System.out.println("-----------------------------------------------------------");
        Optional<User> mayBeCreatedUser = dbServiceUser.getUser(id);

        outputUserOptional("Created user", mayBeCreatedUser);

        user1.setName("Михаил");

        dbServiceUser.saveUser(user1);

        Optional<User> mayBeUpdatedUser = dbServiceUser.getUser(id);
        outputUserOptional("Updated user", mayBeUpdatedUser);

        AddressDataSet addressDataSet2 = new AddressDataSet("Саратов, проспект Новый, 12");
        List<PhoneDataSet> list2 = new ArrayList<>();
        PhoneDataSet phoneDataSet3 = new PhoneDataSet("7122233333");
        PhoneDataSet phoneDataSet4 = new PhoneDataSet("9582121212");
        list2.add(phoneDataSet3);
        list2.add(phoneDataSet4);
        id = dbServiceUser.saveUser(new User("Леонид", addressDataSet2, list2));
        Optional<User> mayBeCreatedUser1 = dbServiceUser.getUser(id);

        outputUserOptional("Created user", mayBeCreatedUser1);
    }

    private static void outputUserOptional(String header, Optional<User> mayBeUser) {
        System.out.println("-----------------------------------------------------------");
        System.out.println(header);
        mayBeUser.ifPresentOrElse(System.out::println, () -> logger.info("User not found"));
    }
}
