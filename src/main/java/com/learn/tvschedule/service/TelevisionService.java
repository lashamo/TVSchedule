package com.learn.tvschedule.service;

import com.learn.tvschedule.model.TelevisionScheduleException;

public interface TelevisionService {

    void addTelevision(String name, String type, int foundedYear) throws TelevisionScheduleException;
}
