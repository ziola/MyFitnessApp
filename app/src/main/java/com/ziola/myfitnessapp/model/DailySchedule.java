package com.ziola.myfitnessapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by mwypysiak on 2015-02-19.
 */
public class DailySchedule {

    private List<Class> studioClasses = new ArrayList<>();
    private List<Class> floorClasses = new ArrayList<>();
    private Date date;

    public void addClass(Class newClass, ROOM type) throws IllegalArgumentException {
        switch(type){
            case FLOOR:
                this.floorClasses.add(newClass);
                Collections.sort(this.floorClasses, Class.ClassComparator);
                break;
            case STUDIO:
                this.studioClasses.add(newClass);
                Collections.sort(this.studioClasses, Class.ClassComparator);
                break;
        }
    }

    public void addStudioClass(Class newClass){
        addClass(newClass, ROOM.STUDIO);
    }

    public void addFloorClass(Class newClass){
        addClass(newClass, ROOM.FLOOR);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
