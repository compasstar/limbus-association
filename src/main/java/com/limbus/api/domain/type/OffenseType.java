package com.limbus.api.domain.type;

import lombok.Getter;

@Getter
public enum OffenseType {
    SLASH("참격"), PIERCE("관통"), BLUNT("타격");

    private final String name;

    private OffenseType(String name) {
        this.name = name;
    }
}
