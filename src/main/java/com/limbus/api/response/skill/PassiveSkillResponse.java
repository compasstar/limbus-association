package com.limbus.api.response.skill;

import com.limbus.api.domain.skill.PassiveSkill;
import com.limbus.api.domain.skill.SkillEffect;
import com.limbus.api.domain.type.PassiveType;
import com.limbus.api.domain.type.SinType;
import lombok.Getter;

@Getter
public class PassiveSkillResponse {

    private String name;
    private SinType sinType;
    private PassiveType passiveType;
    private Integer amount;
    private SkillEffect skillEffect;

    public PassiveSkillResponse(PassiveSkill passiveSkill) {
        name = passiveSkill.getName();
        sinType = passiveSkill.getSinType();
        passiveType = passiveSkill.getPassiveType();
        amount = passiveSkill.getAmount();
        skillEffect = passiveSkill.getSkillEffect();
    }
}