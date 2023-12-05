package ru.tinkoff.qa.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.tinkoff.qa.hibernate.dbmodels.Animal;
import ru.tinkoff.qa.hibernate.dbmodels.Place;
import ru.tinkoff.qa.hibernate.dbmodels.Workman;
import ru.tinkoff.qa.hibernate.dbmodels.Zoo;

public class HibernateSessionFactoryCreator {
    public static SessionFactory createSessionFactory() {
        return new Configuration()
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1")
                .setProperty("hibernate.connection.username", "sa")
                .setProperty("hibernate.connection.password", "sa")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                .addAnnotatedClass(Animal.class)
                .addAnnotatedClass(Place.class)
                .addAnnotatedClass(Workman.class)
                .addAnnotatedClass(Zoo.class)
                .buildSessionFactory();
    }
}
