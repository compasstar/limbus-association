package com.limbus.api.domain.type;

import lombok.Getter;

@Getter
public enum PassiveType {
    OWNED("보유"), RESONANCE("공명");

    private final String name;

    private PassiveType(String name) {
        this.name = name;
    }
}
