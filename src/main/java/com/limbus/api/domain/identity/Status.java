package com.limbus.api.domain.identity;


import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Status {

    private Integer health;
    private String speed;
    private Integer defenseLevel;
}
