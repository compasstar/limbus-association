package com.limbus.api.response.skill;

import com.limbus.api.domain.skill.PassiveSkill;
import com.limbus.api.domain.type.PassiveType;
import com.limbus.api.domain.type.SinType;
import lombok.Getter;

@Getter
public class PassiveSkillResponse {

    private Boolean support;
    private String name;
    private String sinType;
    private String passiveType;
    private Integer amount;
    private String effect;

    public PassiveSkillResponse(PassiveSkill passiveSkill) {
        support = passiveSkill.getSupport();
        name = passiveSkill.getName();
        sinType = (passiveSkill.getSinType() != null) ? passiveSkill.getSinType().getName() : null;
        passiveType = (passiveSkill.getPassiveType() != null) ? passiveSkill.getPassiveType().getName() : null;
        amount = passiveSkill.getAmount();
        effect = passiveSkill.getEffect();
    }
}
