package com.learn.tvschedule.dao;

import com.learn.tvschedule.model.Program;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ProgramDAO {

    List<Program> getProgramsByDateAndTelevisionName(String name, LocalDate date) throws SQLException;

    List<Program> getProgramsByTelevisionAndDate(int televisionId, LocalDate date) throws SQLException;

    List<Program> getAllProgramsByTvNameStartAndEndDate(String televisionName, LocalDate startWeekDate, LocalDate endWeekDate)
            throws SQLException;

    void addProgram(Program program) throws SQLException;

    void deleteProgram(int programId) throws SQLException;

    void deleteProgramsByTelevisionId(int televisionId) throws SQLException;

    List<Program> getAllProgramByDate(LocalDate date) throws SQLException;
}
