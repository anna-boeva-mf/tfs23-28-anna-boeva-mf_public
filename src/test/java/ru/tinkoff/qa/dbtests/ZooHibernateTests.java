package ru.tinkoff.qa.dbtests;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.SelectionQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.tinkoff.qa.hibernate.BeforeCreator;
import ru.tinkoff.qa.hibernate.HibernateSessionFactoryCreator;
import ru.tinkoff.qa.hibernate.dbmodels.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static ru.tinkoff.qa.hibernate.DBZooService.*;

public class ZooHibernateTests {

    @BeforeAll
    static void init() {
        BeforeCreator.createData();
    }

    /**
     * В таблице public.animal ровно 10 записей
     */
    @Test
    public void countRowAnimal() {
        Long resultCount = countRows("Animal");
        int expectedCount = 10;
        Assertions.assertEquals(resultCount, expectedCount, "Check count in public.animal");
    }

    /**
     * В таблицу public.animal нельзя добавить строку с индексом от 1 до 10 включительно
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void insertIndexAnimal(int id) {
        int actualResult = insertAnimal(id, "Animal" + id, 1, 1, 1, 1);
        int expectedResult = -1;
        Assertions.assertEquals(expectedResult, actualResult, "Check inserting into public.animal with id between 1 and 10");
    }

    /**
     * В таблицу public.workman нельзя добавить строку с name = null
     */
    @Test
    public void insertNullToWorkman() {
        int insertResult = insertWorkman(1, null, 1, 1);
        int expectedResult = -1;
        Assertions.assertEquals(expectedResult, insertResult, "Check inserting into public.workman with name = null");
    }

    /**
     * Если в таблицу public.places добавить еще одну строку, то в ней будет 6 строк
     */
    @Test
    public void insertPlacesCountRow() {
        Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession();
        SelectionQuery<?> selectionQuery = session.createSelectionQuery("select max(id) from Place");
        int nextID = (int) selectionQuery.getSingleResult() + 1;
        Transaction transaction = session.beginTransaction();
        Place place = new Place(nextID, 1, 1, "next_place");
        session.persist(place);
        transaction.commit();
        selectionQuery = session.createSelectionQuery("select count(1) from Place");
        Long resultCount = (Long) selectionQuery.getSingleResult();
        session.close();
        int expectedCount = 6;
        Assertions.assertEquals(expectedCount, resultCount, "Check count in public.places after adding new row");
    }


    /**
     * В таблице public.zoo всего три записи с name 'Центральный', 'Северный', 'Западный'
     */
    @Test
    public void countRowZoo() {
        String[] expectedNames = {"Центральный", "Северный", "Западный"};
        int expectedRowCount = 3;
        ArrayList<String> listNames = new ArrayList<>(Arrays.asList(expectedNames));
        Session session = HibernateSessionFactoryCreator.createSessionFactory().openSession();
        SelectionQuery<?> selectionQuery = session.createSelectionQuery("select zoo_name from Zoo");
        List names = selectionQuery.list();
        Iterator iterator = names.iterator();
        int rowCount = 0;
        while (iterator.hasNext()) {
            String name = (String) iterator.next();
            rowCount++;
            if (listNames.contains(name)) {
                listNames.remove(name);
            }
        }
        session.close();
        Assertions.assertArrayEquals(new int[]{expectedRowCount, 0}, new int[]{rowCount, listNames.size()}, "Check count and names in public.zoo");
    }
}