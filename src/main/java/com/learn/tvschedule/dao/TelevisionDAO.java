package com.learn.tvschedule.dao;

import com.learn.tvschedule.model.Television;

import java.sql.SQLException;
import java.util.List;

public interface TelevisionDAO {

    void addTelevision(Television television) throws SQLException;

    List<String> getAllTelevisionName() throws SQLException;

    void televisionEdit() throws SQLException;

    void deleteTelevision() throws SQLException;

    void addProgram()throws  SQLException;

    void deleteProgram() throws SQLException;
}
