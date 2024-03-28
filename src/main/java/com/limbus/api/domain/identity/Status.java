package com.limbus.api.domain.identity;


import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Status {

    private Integer hp;
    private String speed;
    private Integer defenseLevel;

    @Builder
    public Status(Integer hp, String speed, Integer defenseLevel) {
        this.hp = hp;
        this.speed = speed;
        this.defenseLevel = defenseLevel;
    }
}
