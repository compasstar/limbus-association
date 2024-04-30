package com.limbus.api.response;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.identity.Resistances;
import com.limbus.api.domain.identity.Sanity;
import com.limbus.api.domain.identity.Status;
import com.limbus.api.domain.type.Sinner;
import com.limbus.api.response.skill.DefenseSkillResponse;
import com.limbus.api.response.skill.OffenseSkillResponse;
import com.limbus.api.response.skill.PassiveSkillResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class IdentityResponse {

    private String sinner;
    private String name;
    private Integer rarity;
    private Status status;
    private Resistances resistances;
    private Sanity sanity;
    List<OffenseSkillResponse> offenseSkills = new ArrayList<>();
    List<DefenseSkillResponse> defenseSkills = new ArrayList<>();
    List<PassiveSkillResponse> passiveSkills = new ArrayList<>();

    public IdentityResponse(Identity identity) {
        sinner = identity.getSinner() != null ? identity.getSinner().getName() : null;
        name = identity.getName();
        rarity = identity.getRarity();
        status = identity.getStatus();
        resistances = identity.getResistances();
        sanity = identity.getSanity();
        identity.getOffenseSkills().forEach(offenseSkill -> offenseSkills.add(new OffenseSkillResponse(offenseSkill)));
        identity.getDefenseSkills().forEach(defenseSkill -> defenseSkills.add(new DefenseSkillResponse(defenseSkill)));
        identity.getPassiveSkills().forEach(passiveSkill -> passiveSkills.add(new PassiveSkillResponse(passiveSkill)));
    }
}
