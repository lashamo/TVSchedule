package com.learn.tvschedule.dao;

import com.learn.tvschedule.model.Television;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelevisionDAOImpl implements TelevisionDAO {

    @Override
    public void addTelevision(Television television) throws SQLException {
        Connection connection = DaoUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Television (name,type,founded_year) " +
                "VALUES (?,?,?)");

        preparedStatement.setString(1, television.getName());
        preparedStatement.setString(2, television.getType().toString());
        preparedStatement.setInt(3, television.getFoundedYear());

        preparedStatement.executeUpdate();
    }

    @Override
    public List<String> getAllTelevisionName() throws SQLException {
        Connection connection = DaoUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name FROM Television");
        List<String> names = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            names.add(name);
        }
        return names;
    }

    @Override
    public void televisionEdit() throws SQLException {

    }

    @Override
    public void deleteTelevision() throws SQLException {

    }

    @Override
    public void addProgram() throws SQLException {

    }

    @Override
    public void deleteProgram() throws SQLException {

    }
}
