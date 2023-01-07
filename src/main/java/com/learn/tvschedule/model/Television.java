package com.learn.tvschedule.model;

import java.util.ArrayList;
import java.util.List;

public class Television {
    private int id;
    private String name;
    private TelevisionType type;
    private int foundedYear;
    private List<Program> programs = new ArrayList<>();

    public Television() {
    }

    public Television(int id, String name, TelevisionType type, int foundedYear) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.foundedYear = foundedYear;
    }

    public Television(int id, String name,  int foundedYear) {
        this.id = id;
        this.name = name;
        this.foundedYear = foundedYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TelevisionType getType() {
        return type;
    }

    public void setType(TelevisionType type) {
        this.type = type;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    @Override
    public String toString() {
        return "Television{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", foundedYear=" + foundedYear +
                '}';
    }

    public String showIdNameFoundedYear(){
        return "television{ id = " + id + "name =" + name + "foundedYear = " + foundedYear;
    }
}
