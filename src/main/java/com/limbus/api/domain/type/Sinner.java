package com.limbus.api.domain.type;

import lombok.Getter;

@Getter
public enum Sinner {
    YI_SANG("이상"), FAUST("파우스트"), DON_QUIXOTE("돈키호테"), RYOSHU("로슈"), MEURSAULT("뫼르소"), HONG_LU("홍루"), HEATHCLIFF("히스클리프"), ISHMAEL("이스마엘"), RODION("로쟈"), EMIL_SINCLAIR("싱클레어"), OUTIS("오티스"), GREGOR("그레고르");

    private final String name;

    private Sinner(String name) {
        this.name = name;
    }
}
