package com.limbus.api.domain.identity;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Resistances {

    private double slashResistance;
    private double pierceResistance;
    private double bluntResistance;

    @Builder
    public Resistances(double slashResistance, double pierceResistance, double bluntResistance) {
        this.slashResistance = slashResistance;
        this.pierceResistance = pierceResistance;
        this.bluntResistance = bluntResistance;
    }
}
