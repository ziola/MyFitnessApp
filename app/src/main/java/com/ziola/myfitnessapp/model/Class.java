package com.ziola.myfitnessapp.model;

import java.util.Comparator;

/**
 * Created by mwypysiak on 2015-02-19.
 */
public class Class {

    public Class(String name, String trainer, String startTime, String endTime, ROOM room) {
        this.trainer = trainer;
        this.startTime = startTime;
        this.name = name;
        this.room = room;
        this.endTime = endTime;
    }

    public static final Comparator<Class> ClassComparator = new Comparator<Class>() {
        @Override
        public int compare(Class lhs, Class rhs) {
            int result = lhs.getStartTime().compareTo(rhs.getStartTime());
            if(result != 0) {
                return result;
            }
            return lhs.getRoom().getPriority() - rhs.getRoom().getPriority();
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ROOM getRoom() {
        return room;
    }

    public void setRoom(ROOM room) {
        this.room = room;
    }

    private String name;
    private String trainer;
    private String startTime;
    private String endTime;
    private ROOM room;

}
