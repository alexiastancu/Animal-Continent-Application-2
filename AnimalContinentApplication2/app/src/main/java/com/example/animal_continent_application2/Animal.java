package com.example.animal_continent_application2;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Animal other = (Animal) obj;
        return Objects.equals(name, other.name) && Objects.equals(continent, other.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, continent);
    }

}

