package com.example.catestandroid.model;

public class CatBreed {
    public String id, name;
    public Cat cat;

    @Override
    public String toString() {
        return "Breed{" +
                "id='" + id + '\'' +
                ", name='" + name +
                '}';
    }
}
