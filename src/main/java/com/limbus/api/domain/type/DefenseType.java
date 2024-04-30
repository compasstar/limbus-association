package com.limbus.api.domain.type;

import lombok.Getter;

@Getter
public enum DefenseType {
    DEFENSE("수비"), COUNTER("반격"), EVASION("회피");

    private final String name;

    private DefenseType(String name) {
        this.name = name;
    }
}
