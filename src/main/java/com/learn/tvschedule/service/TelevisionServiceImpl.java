package com.learn.tvschedule.service;

import com.learn.tvschedule.dao.*;
import com.learn.tvschedule.model.Program;
import com.learn.tvschedule.model.Television;
import com.learn.tvschedule.model.TelevisionScheduleException;
import com.learn.tvschedule.model.TelevisionType;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TelevisionServiceImpl implements TelevisionService {

    private static TelevisionDAO televisionDAO = new TelevisionDAOImpl();
    private static ProgramDAO programDAO = new ProgramDAOImpl();

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void addTelevision(String name, String type, int foundedYear) throws TelevisionScheduleException {
        try {
            // Check if name already exists
            List<String> televisionNames = televisionDAO.getAllTelevisionName();
            for (String n : televisionNames) {
                if (name.equals(n)) {
                    throw new TelevisionScheduleException(String.format("Television with name %s already exists", name));
                }
            }

            // Insert television
            Television television = new Television();
            television.setName(name);
            television.setType(TelevisionType.valueOf(type));
            television.setFoundedYear(foundedYear);
            televisionDAO.addTelevision(television);
        } catch (SQLException ex) {
            throw new TelevisionScheduleException(ex.getMessage());
        }
    }

    @Override
    public List<Television> getAllTelevision() throws TelevisionScheduleException {
        try {
            return televisionDAO.getAllTelevision();
        } catch (SQLException ex) {
            throw new TelevisionScheduleException(ex.getMessage());
        }
    }

    @Override
    public void addProgram(String name, String dateString, String startTimeString, String endTimeString, int televisionId)
            throws TelevisionScheduleException {
        try {
            LocalDate date = LocalDate.parse(dateString, dateFormatter);
            LocalTime startTime = LocalTime.parse(startTimeString, timeFormatter);
            LocalTime endTime = LocalTime.parse(endTimeString, timeFormatter);
            List<Program> programs = programDAO.getProgramsByTelevisionAndDate(televisionId, date);

            for (Program program : programs) {
                if (program.getStartTime().isAfter(startTime) && program.getStartTime().isBefore(endTime)) {
                    throw new TelevisionScheduleException("Program has intersection with another program");
                } else if (program.getEndTime().isAfter(startTime) && program.getEndTime().isBefore(endTime)) {
                    throw new TelevisionScheduleException("Program has intersection with another program");
                }
            }

            Program program = new Program();
            program.setName(name);
            program.setDate(date);
            program.setStartTime(startTime);
            program.setEndTime(endTime);
            program.setTelevisionId(televisionId);
            programDAO.addProgram(program);
        } catch (SQLException ex) {
            throw new TelevisionScheduleException(ex.getMessage());
        }
    }

    @Override
    public List<Program> getProgramsByDateAndTelevisionName(String televisionName, String date) throws TelevisionScheduleException {
        try {
            return programDAO.getProgramsByDateAndTelevisionName(televisionName, LocalDate.parse(date, dateFormatter));
        } catch (SQLException ex) {
            throw new TelevisionScheduleException(ex.getMessage());
        }
    }

    @Override
    public List<Program> getAllNextWeekProgramsByTvName(String televisionName) throws TelevisionScheduleException {
        try {
            LocalDate now = LocalDate.now();
            LocalDate nextWeek = now.plus(1, ChronoUnit.WEEKS);

            return programDAO.getAllProgramsByTvNameStartAndEndDate(televisionName, now, nextWeek);
        } catch (SQLException ex) {
            throw new TelevisionScheduleException(ex.getMessage());
        }
    }

    @Override
    public void deleteProgram(String programId) throws TelevisionScheduleException {
        try {
            programDAO.deleteProgram(Integer.parseInt(programId));
        } catch (SQLException ex) {
            throw new TelevisionScheduleException(ex.getMessage());
        }
    }

    @Override
    public void deleteTelevision(String televisionId) throws TelevisionScheduleException {
        // Two DAO methods must be transactional
        try {
            Connection connection = DaoUtils.getConnection();
            connection.setAutoCommit(false);  // Begin transaction
            programDAO.deleteProgramsByTelevisionId(Integer.parseInt(televisionId));
            televisionDAO.deleteTelevision(Integer.parseInt(televisionId));
            connection.commit();   // End transaction
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            throw new TelevisionScheduleException(ex.getMessage());
        }
    }

    @Override
    public List<Television> getAllTelevisionByType(String typeName) throws TelevisionScheduleException {
        try {
            return televisionDAO.getAllTelevisionByType(typeName);
        } catch (SQLException ex) {
            throw new TelevisionScheduleException(ex.getMessage());
        }
    }

    @Override
    public List<Program> getAllTelevisionProgramByDate() throws TelevisionScheduleException {
        try {
            return programDAO.getAllProgramByDate(LocalDate.now());
        } catch (SQLException ex) {
            throw new TelevisionScheduleException(ex.getMessage());
        }
    }
}
