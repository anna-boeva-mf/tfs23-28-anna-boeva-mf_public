package ru.tinkoff.qa.hibernate.dbmodels;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "zoo")
public class Zoo {
    @Id
    int id;
    @Column(name = "\"name\"")
    String zoo_name;

    public Zoo(int id, String zoo_name) {
        this.id = id;
        this.zoo_name = zoo_name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return zoo_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.zoo_name = name;
    }
}
