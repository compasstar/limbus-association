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
    private Integer minSpeed;
    private Integer maxSpeed;
    private Integer defenseLevel;

    @Builder
    public Status(Integer hp, Integer minSpeed, Integer maxSpeed, Integer defenseLevel) {
        this.hp = hp;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.defenseLevel = defenseLevel;
    }
}
