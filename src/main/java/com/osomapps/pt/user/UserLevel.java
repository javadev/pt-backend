package com.osomapps.pt.user;

public enum UserLevel {
    Unexperienced(1),
    Experienced(2);

    private final int level;
    private UserLevel(int level) {
        this.level = level;
    }

    int getLevel() {
        return level;
    }

    public static UserLevel of(int level) {
        for (UserLevel userLevel : values()) {
            if (userLevel.level == level) {
                return userLevel;
            }
        }
        return null;
    }
}
