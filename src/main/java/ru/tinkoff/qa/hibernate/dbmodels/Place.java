package ru.tinkoff.qa.hibernate.dbmodels;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "places")
public class Place {
    @Id
    int id;
    @Column(name = "\"row\"")
    int row;
    @Column(name = "place_num")
    int placeNum;
    @Column(name = "\"name\"")
    String name;

    public Place(int id, int row, int placeNum, String name) {
        this.id = id;
        this.row = row;
        this.placeNum = placeNum;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setPlaceNum(int placeNum) {
        this.placeNum = placeNum;
    }

    public void setName(String name) {
        this.name = name;
    }
}
