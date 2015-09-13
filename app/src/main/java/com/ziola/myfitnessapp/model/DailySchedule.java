package com.ziola.myfitnessapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mwypysiak on 2015-02-19.
 */
public class DailySchedule {

    private Map<ROOM, List<Class>> classes = new HashMap<ROOM, List<Class>>();

    private Date date;



    public List<Class> getClass(ROOM type) {
        return classes.get(type);
    }

    public void addClass(Class newClass, ROOM type) throws IllegalArgumentException {
        List<Class> list = classes.get(type);
        if(list == null) {
            list = new ArrayList<Class>();
        }
        list.add(newClass);
        Collections.sort(list, Class.ClassComparator);
        classes.put(type, list);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
