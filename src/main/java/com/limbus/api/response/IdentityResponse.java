package com.limbus.api.response;

import com.limbus.api.domain.identity.Resistances;
import com.limbus.api.domain.identity.Sanity;
import com.limbus.api.domain.identity.Status;
import com.limbus.api.response.skill.DefenseSkillResponse;
import com.limbus.api.response.skill.OffenseSkillResponse;
import com.limbus.api.response.skill.PassiveSkillResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class IdentityResponse {

    private String name;
    private Integer rarity;
    private Status status;
    private Resistances resistances;
    private List<OffenseSkillResponse> offenseSkills = new ArrayList<>();
    private DefenseSkillResponse defenseSkill;
    private PassiveSkillResponse passiveSkill;
    private Sanity sanity;

}
