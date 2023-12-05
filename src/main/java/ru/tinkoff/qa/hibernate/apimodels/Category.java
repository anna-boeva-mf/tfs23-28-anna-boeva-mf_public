package ru.tinkoff.qa.hibernate.apimodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private int id;

    public Category() {
    }

    public Category(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}