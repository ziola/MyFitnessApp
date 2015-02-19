package com.ziola.myfitnessapp.model;

import java.util.List;
import java.util.Map;

/**
 * Created by mwypysiak on 2015-02-19.
 */
public class Schedule {

    public Map<String, DailySchedule> getDailyClasses() {
        return dailyClasses;
    }

    public void setDailyClasses(Map<String, DailySchedule> dailyClasses) {
        this.dailyClasses = dailyClasses;
    }

    public void addSchedule(String date, DailySchedule schedule){
        this.dailyClasses.put(date, schedule);
    }

    private Map<String, DailySchedule> dailyClasses;
}
