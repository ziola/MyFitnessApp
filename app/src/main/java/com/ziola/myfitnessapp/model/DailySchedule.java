package com.ziola.myfitnessapp.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mwypysiak on 2015-02-19.
 */
public class DailySchedule {

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public void addClass(Class newClass){
        this.classes.add(newClass);
        Collections.sort(this.classes, Class.ClassComparator);
    }

    private List<Class> classes;
}
