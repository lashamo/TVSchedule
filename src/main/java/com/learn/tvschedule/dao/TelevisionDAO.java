package com.learn.tvschedule.dao;

import com.learn.tvschedule.model.Program;
import com.learn.tvschedule.model.Television;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface TelevisionDAO {

    void addTelevision(Television television) throws SQLException;

    List<String> getAllTelevisionName() throws SQLException;

    List<Television> getAllTelevision()throws SQLException;

    List<Program> getAllProgram() throws  SQLException;

    List<Program> getProgramsByTelevisionAndDate(int televisionId, LocalDate date) throws  SQLException;

    void televisionEdit() throws SQLException;

    void deleteTelevision() throws SQLException;

    void addProgram(Program program) throws  SQLException;

    void deleteProgram() throws SQLException;
}
