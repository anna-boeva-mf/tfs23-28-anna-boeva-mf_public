package ru.tinkoff.qa.hibernate;

import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import ru.tinkoff.qa.hibernate.dbmodels.Animal;
import ru.tinkoff.qa.hibernate.dbmodels.Place;
import ru.tinkoff.qa.hibernate.dbmodels.Workman;

public class DBZooService {
    public static Long countRows(String tableName) {
        Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession();
        SelectionQuery<?> selectionQuery = session.createSelectionQuery("select count(1) from " + tableName);
        Long i = (Long) selectionQuery.getSingleResult();
        session.close();
        return i;
    }

    public static int insertAnimal(int id, String name, int age, int type, int sex, int place) {
        Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Animal animal = new Animal(id, name, age, type, sex, place);
        try {
            session.persist(animal);
            transaction.commit();
        } catch (PersistenceException e) {
            return -1;
        } catch (Exception e) {
            return -2;
        } finally {
            session.close();
        }
        return 1;
    }

    public static int insertWorkman(int id, String name, int age, int position) {
        Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Workman workman = new Workman(id, name, age, position);
        try {
            session.persist(workman);
            transaction.commit();
        } catch (PersistenceException e) {
            return -1;
        } catch (Exception e) {
            return -2;
        } finally {
            session.close();
        }
        return 1;
    }

    public static int insertPlace(int id, int row, int placeNum, String name) {
        Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Place place = new Place(id, row, placeNum, name);
        try {
            session.persist(place);
            transaction.commit();
        } catch (PersistenceException e) {
            return -1;
        } catch (Exception e) {
            return -2;
        } finally {
            session.close();
        }
        return 1;
    }
}
