package ru.tinkoff.qa.hibernate.dbmodels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    int id;
    @Column(name = "\"name\"")
    String name;
    @Column(name = "age")
    int age;
    @Column(name = "\"type\"")
    int type;
    @Column(name = "sex")
    int sex;
    @Column(name = "place")
    int place;

    public Animal(int id, String name, int age, int type, int sex, int place) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.sex = sex;
        this.place = place;
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

    public int getType() {
        return type;
    }

    public int getSex() {
        return sex;
    }

    public int getPlace() {
        return place;
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

    public void setType(int type) {
        this.type = type;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
