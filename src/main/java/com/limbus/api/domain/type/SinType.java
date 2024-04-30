package com.limbus.api.domain.type;

import lombok.Getter;

@Getter
public enum SinType {
    WRATH("분노"), LUST("질투"), SLOTH("나태"), GLUT("탐식"), GLOOM("우울"), PRIDE("오만"), ENVY("질투");

    private final String name;
    private SinType(String name) {
        this.name = name;
    }
}
