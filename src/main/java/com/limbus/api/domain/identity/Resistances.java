package com.limbus.api.domain.identity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Resistances {

    private double slashResistance;
    private double pierceResistance;
    private double bluntResistance;
}
