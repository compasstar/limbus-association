package com.limbus.api.response;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.identity.Resistances;
import com.limbus.api.domain.identity.Sanity;
import com.limbus.api.domain.identity.Status;
import com.limbus.api.domain.skill.OffenseSkill;
import com.limbus.api.domain.type.Sinner;
import lombok.Getter;

@Getter
public class IdentityResponse {

    private Sinner sinner;
    private String name;
    private Integer rarity;
    private Status status;
    private Resistances resistances;
    private Sanity sanity;

    public IdentityResponse(Identity identity) {
        sinner = identity.getSinner();
        name = identity.getName();
        rarity = identity.getRarity();
        status = identity.getStatus();
        resistances = identity.getResistances();
        sanity = identity.getSanity();
    }
}
