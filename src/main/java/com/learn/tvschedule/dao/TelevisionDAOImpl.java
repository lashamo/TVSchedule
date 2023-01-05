package com.learn.tvschedule.dao;

import com.learn.tvschedule.model.Program;
import com.learn.tvschedule.model.Television;
import com.learn.tvschedule.model.TelevisionType;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
        return televisions;
    }


    @Override
    public void televisionEdit() throws SQLException {

    }

    @Override
    public void deleteTelevision() throws SQLException {

    }

    @Override
    public void addProgram(Program program) throws SQLException {
        Connection connection = DaoUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Program (name,date,start_time," +
                "end_time,television_id) VALUES (?,?,?,?,?)");

        preparedStatement.setString(1, program.getName());
        preparedStatement.setDate(2, Date.valueOf(program.getDate()));
        preparedStatement.setTime(3, Time.valueOf(program.getStartTime()));
        preparedStatement.setTime(4, Time.valueOf(program.getEndTime()));
        preparedStatement.setInt(5, program.getTelevisionId());

        preparedStatement.executeUpdate();
    }

    @Override
    public List<Program> getAllProgram() throws SQLException {
        Connection connection = DaoUtils.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Program");
        List<Program> programs = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            LocalDate date = LocalDate.parse((CharSequence) resultSet.getDate("date"));
            LocalTime startTime = LocalTime.parse((CharSequence) resultSet.getTime("start_time"));
            LocalTime endTime = LocalTime.parse((CharSequence) resultSet.getTime("end_time"));
            int televisionId = resultSet.getInt("television_id");
            Program program = new Program(id, name, date, startTime, endTime, televisionId);
            programs.add(program);
        }

        return programs;
    }

    @Override
    public List<Program> getProgramsByTelevisionAndDate(int televisionId, LocalDate date) throws SQLException {
        List<Program> programs = new ArrayList<>();
        Connection connection = DaoUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT start_time, end_time FROM program WHERE " +
                "television_id = ? AND date = ?");
        preparedStatement.setInt(1, televisionId);
        preparedStatement.setDate(2, Date.valueOf(date));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            LocalTime startTime = resultSet.getTime("start_time").toLocalTime();
            LocalTime endTime = resultSet.getTime("end_time").toLocalTime();
            Program program = new Program(date, startTime, endTime);
            programs.add(program);
        }

        return programs;
    }

    @Override
    public void deleteProgram() throws SQLException {

    }
}
