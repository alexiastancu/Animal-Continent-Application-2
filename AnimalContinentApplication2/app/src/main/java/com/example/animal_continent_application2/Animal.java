package com.example.animal_continent_application2;

public class Animal {
    private long id;
    private String name;
    private String continent;

    public Animal(long id, String name, String continent) {
        this.id = id;
        this.name = name;
        this.continent = continent;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    @Override
    public String toString() {
        return name + "\n" + continent;
    }

    public void setId(long id) {
        this.id = id;
    }
}

