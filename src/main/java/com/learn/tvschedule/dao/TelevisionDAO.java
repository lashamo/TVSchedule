package com.learn.tvschedule.dao;

import com.learn.tvschedule.model.Television;

import java.sql.SQLException;
import java.util.List;

public interface TelevisionDAO {

    void addTelevision(Television television) throws SQLException;

    List<String> getAllTelevisionName() throws SQLException;

    List<Television> getAllTelevision()throws SQLException;

    void deleteTelevision(int televisionId) throws SQLException;

    void televisionEdit() throws SQLException;

    List<Television> getAllTelevisionByType(String typeName) throws SQLException;
}
