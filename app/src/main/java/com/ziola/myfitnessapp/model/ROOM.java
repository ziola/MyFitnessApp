package com.ziola.myfitnessapp.model;

/**
 * Created by mwypysiak on 2015-02-19.
 */
public enum ROOM {

    STUDIO("Studio"),
    FLOOR("Gym Floor");

    private final String name;

    ROOM(String name) {
        this.name = name;
    }

    public static ROOM get(String value) {
        for (ROOM room : values()) {
            if (room.getName().equals(value)) {
                return room;
            }
        }
        throw new IllegalArgumentException(value + " is not associated with any value of " + ROOM.class.getCanonicalName());
    }

    public String getName() {
        return this.name;
    }
}
