package com.ziola.myfitnessapp.model;

/**
 * Created by mwypysiak on 2015-02-19.
 */
public enum ROOM {

    STUDIO("Studio", 0),
    FLOOR("Gym floor", 1);

    private final String name;
    private final int priority;

    ROOM(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName(){
        return this.name;
    }

    public int getPriority(){
        return this.priority;
    }
}
