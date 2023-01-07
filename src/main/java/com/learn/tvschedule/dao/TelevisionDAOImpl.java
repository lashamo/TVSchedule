package com.learn.tvschedule.dao;

import com.learn.tvschedule.model.Television;
import com.learn.tvschedule.model.TelevisionType;

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

        preparedStatement.close();
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

        resultSet.close();
        statement.close();

        return names;
    }

    @Override
    public List<Television> getAllTelevision() throws SQLException {
        Connection connection = DaoUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Television");
        List<Television> televisions = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            TelevisionType televisionType = TelevisionType.valueOf(resultSet.getString("type"));
            int foundedYear = resultSet.getInt("founded_year");
            Television television = new Television(id, name, televisionType, foundedYear);
            televisions.add(television);
        }

        resultSet.close();
        connection.close();

        return televisions;
    }


    @Override
    public void televisionEdit() throws SQLException {

    }

    @Override
    public List<Television> getAllTelevisionByType(String typeName) throws SQLException {
        List<Television> televisions = new ArrayList<>();
        Connection connection = DaoUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Television WHERE type = ?");
        preparedStatement.setString(1, typeName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            TelevisionType type = TelevisionType.valueOf(resultSet.getString("type"));
            int foundedYear = resultSet.getInt("founded_year");
            Television television = new Television(id, name, type, foundedYear);
            televisions.add(television);
        }

        resultSet.close();
        preparedStatement.close();

        return televisions;
    }

    @Override
    public void deleteTelevision(int televisionId) throws SQLException {
        Connection connection = DaoUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Television WHERE id = ?");
        preparedStatement.setInt(1, televisionId);
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }
}
