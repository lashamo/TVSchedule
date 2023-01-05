package com.learn.tvschedule.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Program {
    private int id;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int televisionId;

    public Program() {
    }

    public Program(LocalDate date, LocalTime startTime, LocalTime endTime){
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Program(int id, String name, LocalDate date, LocalTime startTime, LocalTime endTime, int televisionId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.televisionId = televisionId;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getTelevisionId() {
        return televisionId;
    }

    public void setTelevisionId(int televisionId) {
        this.televisionId = televisionId;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id =" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", televisionId=" + televisionId +
                '}';
    }
}
