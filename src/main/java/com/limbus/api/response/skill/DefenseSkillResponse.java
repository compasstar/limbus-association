package com.limbus.api.response.skill;

import com.limbus.api.domain.skill.DefenseSkill;
import com.limbus.api.domain.type.DefenseType;
import com.limbus.api.domain.type.SinType;
import lombok.Getter;

@Getter
public class DefenseSkillResponse {

    private String name;
    private Integer level;
    private DefenseType defenseType;
    private SinType sinType;
    private Integer skillPower;
    private Integer coinPower;
    private String effect;

    public DefenseSkillResponse(DefenseSkill defenseSkill) {
        name = defenseSkill.getName();
        level = defenseSkill.getLevel();
        defenseType = defenseSkill.getDefenseType();
        sinType = defenseSkill.getSinType();
        skillPower = defenseSkill.getSkillPower();
        coinPower = defenseSkill.getCoinPower();
        effect = defenseSkill.getEffect();
    }
}
