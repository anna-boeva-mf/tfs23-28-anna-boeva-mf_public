package ru.tinkoff.qa.hibernate.dbmodels;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "workman")
public class Workman {
    @Id
    int id;
    @Column(name = "\"name\"")
    String name;
    @Column(name = "age")
    int age;
    @Column(name = "\"position\"")
    int position;

    public Workman(int id, String name, int age, int position) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getPosition() {
        return position;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
