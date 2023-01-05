package com.learn.tvschedule.service;

import com.learn.tvschedule.model.Program;
import com.learn.tvschedule.model.Television;
import com.learn.tvschedule.model.TelevisionScheduleException;

import java.util.List;

public interface TelevisionService {

    void addTelevision(String name, String type, int foundedYear) throws TelevisionScheduleException;

    List<Television> getAllTelevision() throws TelevisionScheduleException;

    void addProgram(String name, String date, String startTime, String endTime , int televisionId) throws TelevisionScheduleException;

    List<Program> getAllProgram() throws TelevisionScheduleException;
}
