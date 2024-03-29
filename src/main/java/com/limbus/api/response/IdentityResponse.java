package com.limbus.api.response;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.identity.Resistances;
import com.limbus.api.domain.identity.Sanity;
import com.limbus.api.domain.identity.Status;
import com.limbus.api.response.skill.DefenseSkillResponse;
import com.limbus.api.response.skill.OffenseSkillResponse;
import com.limbus.api.response.skill.PassiveSkillResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class IdentityResponse {

    private String name;
    private Integer rarity;
    private Status status;
    private Resistances resistances;
    private List<OffenseSkillResponse> offenseSkills;
    private DefenseSkillResponse defenseSkill;
    private List<PassiveSkillResponse> passiveSkills;
    private Sanity sanity;

    public IdentityResponse(Identity identity) {
        name = identity.getName();
        rarity = identity.getRarity();
        status = identity.getStatus();
        resistances = identity.getResistances();
        offenseSkills = identity.getOffenseSkills().stream().map(OffenseSkillResponse::new).collect(Collectors.toList());
        defenseSkill = new DefenseSkillResponse(identity.getDefenseSkill());
        passiveSkills = identity.getPassiveSkills().stream().map(PassiveSkillResponse::new).collect(Collectors.toList());
        sanity = identity.getSanity();
    }
}