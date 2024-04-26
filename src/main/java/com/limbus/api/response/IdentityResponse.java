package com.limbus.api.response;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.identity.Resistances;
import com.limbus.api.domain.identity.Sanity;
import com.limbus.api.domain.identity.Status;
import com.limbus.api.response.skill.DefenseSkillResponse;
import com.limbus.api.response.skill.OffenseSkillResponse;
import com.limbus.api.response.skill.PassiveSkillResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class IdentityResponse {

    private String name;
    private Integer rarity;
    private Status status;
    private Resistances resistances;
    private List<OffenseSkillResponse> offenseSkills = new ArrayList<>();
    private List<DefenseSkillResponse> defenseSkills = new ArrayList<>();
    private List<PassiveSkillResponse> passiveSkills = new ArrayList<>();
    private Sanity sanity;

    public IdentityResponse(Identity identity) {
        name = identity.getName();
        rarity = identity.getRarity();
        status = identity.getStatus();
        resistances = identity.getResistances();
        System.out.println("identity.getOffenseSkills().get(0).getName() = " + identity.getOffenseSkills().get(0).getName());
        if (identity.getOffenseSkills() != null) {
            offenseSkills = identity.getOffenseSkills().stream().map(OffenseSkillResponse::new).collect(Collectors.toList());
//            System.out.println("getOffenseSkills = " + identity.getOffenseSkills().size());
//            System.out.println("offenseSkills = " + offenseSkills.size());
        }
        if (identity.getDefenseSkills() != null) {
            defenseSkills = identity.getDefenseSkills().stream().map(DefenseSkillResponse::new).collect(Collectors.toList());
        }
        if (identity.getPassiveSkills() != null) {
            passiveSkills = identity.getPassiveSkills().stream().map(PassiveSkillResponse::new).collect(Collectors.toList());
        }
        sanity = identity.getSanity();
    }
}
