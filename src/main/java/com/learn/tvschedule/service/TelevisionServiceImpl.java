package com.learn.tvschedule.service;

import com.learn.tvschedule.dao.TelevisionDAO;
import com.learn.tvschedule.dao.TelevisionDAOImpl;
import com.learn.tvschedule.model.Television;
import com.learn.tvschedule.model.TelevisionScheduleException;
import com.learn.tvschedule.model.TelevisionType;

import java.sql.SQLException;
import java.util.List;

public class TelevisionServiceImpl implements TelevisionService {

    private static TelevisionDAO televisionDAO = new TelevisionDAOImpl();

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
            throw new TelevisionScheduleException(ex.getSQLState());
        }
    }
}
