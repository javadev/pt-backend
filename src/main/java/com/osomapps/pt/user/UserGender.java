package com.osomapps.pt.user;

import java.util.Optional;

public enum UserGender {
    Male("male"),
    Female("female");

    private final String gender;

    private UserGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public static Optional<UserGender> of(String gender) {
        for (UserGender userGender : values()) {
            if (userGender.gender.equals(gender)) {
                return Optional.of(userGender);
            }
        }
        return Optional.empty();
    }
}
