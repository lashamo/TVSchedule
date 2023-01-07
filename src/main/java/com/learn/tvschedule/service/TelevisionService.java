package com.learn.tvschedule.service;

import com.learn.tvschedule.model.Program;
import com.learn.tvschedule.model.Television;
import com.learn.tvschedule.model.TelevisionScheduleException;

import java.util.List;

public interface TelevisionService {

    void addTelevision(String name, String type, int foundedYear) throws TelevisionScheduleException;

    List<Television> getAllTelevision() throws TelevisionScheduleException;

    void addProgram(String name, String date, String startTime, String endTime, int televisionId) throws TelevisionScheduleException;

    List<Program> getProgramsByDateAndTelevisionName(String televisionName, String date) throws TelevisionScheduleException;

    List<Program> getAllNextWeekProgramsByTvName(String televisionName) throws TelevisionScheduleException;

    void deleteProgram(String programId) throws TelevisionScheduleException;

    void deleteTelevision(String televisionId) throws TelevisionScheduleException;

    List<Television> getAllTelevisionByType(String typeName) throws TelevisionScheduleException;

    List<Program> getAllTelevisionProgramByDate() throws TelevisionScheduleException;

}
