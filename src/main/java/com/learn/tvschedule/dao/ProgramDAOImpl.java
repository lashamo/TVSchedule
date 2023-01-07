package com.learn.tvschedule.dao;

import com.learn.tvschedule.model.Program;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {

    @Override
    public List<Program> getProgramsByDateAndTelevisionName(String nameTelevision, LocalDate date) throws SQLException {
        List<Program> programs = new ArrayList<>();
        Connection connection = DaoUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT Program.name AS programName, " +
                "Program.id, Program.start_time, Program.end_time, Program.television_id " +
                "FROM Program INNER JOIN Television ON Program.television_id = Television.id WHERE " +
                "Television.name = ? AND Program.date = ?");
        preparedStatement.setString(1, nameTelevision);
        preparedStatement.setDate(2, Date.valueOf(date));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String programName = resultSet.getString("programName");
            LocalTime startTime = resultSet.getTime("start_time").toLocalTime();
            LocalTime endTime = resultSet.getTime("end_time").toLocalTime();
            int televisionId = resultSet.getInt("television_id");
            Program program = new Program(id, programName, date, startTime, endTime, televisionId);
            programs.add(program);
        }

        preparedStatement.close();

        return programs;
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

        preparedStatement.close();
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

        preparedStatement.close();

        return programs;
    }

    @Override
    public List<Program> getAllProgramsByTvNameStartAndEndDate(String televisionName, LocalDate startDate, LocalDate endDate)
            throws SQLException {
        List<Program> programs = new ArrayList<>();
        Connection connection = DaoUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT Program.name AS programName, " +
                "Program.start_time, Program.end_time, Television.id, Program.id AS programID " +
                "FROM Program INNER JOIN Television ON Program.television_id = Television.id WHERE " +
                "Television.name = ? AND Program.date >= ? AND Program.date <= ? ");

        preparedStatement.setString(1, televisionName);
        preparedStatement.setDate(2, Date.valueOf(startDate));
        preparedStatement.setDate(3, Date.valueOf(endDate));
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String programName = resultSet.getString("programName");
            LocalTime startTime = resultSet.getTime("start_time").toLocalTime();
            LocalTime endTime = resultSet.getTime("end_time").toLocalTime();
            int televisionId = resultSet.getInt("id");
            int programID = resultSet.getInt("programId");
            Program program = new Program(programName, startTime, endTime, televisionId, programID);
            programs.add(program);
        }

        resultSet.close();
        preparedStatement.close();

        return programs;
    }

    @Override
    public void deleteProgram(int programId) throws SQLException {
        Connection connection = DaoUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PROGRAM WHERE id = ?");
        preparedStatement.setInt(1, programId);
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public void deleteProgramsByTelevisionId(int televisionId) throws SQLException {
        Connection connection = DaoUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Program WHERE television_id = ?");
        preparedStatement.setInt(1, televisionId);
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public List<Program> getAllProgramByDate(LocalDate date) throws SQLException {
        Connection connection = DaoUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT Program.name AS programName,Program.date," +
                "Program.start_time,Program.end_time,Program.television_id, Television.name " +
                "FROM Program INNER JOIN Television ON Program.television_id = Television.id WHERE date = ?");
        preparedStatement.setDate(1, Date.valueOf(date));
        List<Program> programs = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String programName = resultSet.getString("programName");
            LocalDate programDate = resultSet.getDate("date").toLocalDate();
            LocalTime startTime = resultSet.getTime("start_time").toLocalTime();
            LocalTime endTime = resultSet.getTime("end_time").toLocalTime();
            int televisionId = resultSet.getInt("television_id");
            String name = resultSet.getString("name");
            Program program = new Program(programName, programDate, startTime, endTime, televisionId, name);
            programs.add(program);
        }

        resultSet.close();
        preparedStatement.close();

        return programs;
    }
}
