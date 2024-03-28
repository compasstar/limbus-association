package com.limbus.api.response.skill;

import com.limbus.api.domain.skill.SkillEffect;
import com.limbus.api.domain.skill.OffenseSkill;
import com.limbus.api.domain.type.OffenseType;
import com.limbus.api.domain.type.SinType;
import lombok.Getter;

@Getter
public class OffenseSkillResponse {

    private Integer slot;
    private String name;
    private Integer level;
    private OffenseType offenseType;
    private SinType sinType;
    private Integer amount;
    private Integer skillPower;
    private Integer coinNumber;
    private Integer weight;
    private SkillEffect skillEffect;


    public OffenseSkillResponse(OffenseSkill offenseSkill) {
        slot = offenseSkill.getSlot();
        name = offenseSkill.getName();
        level = offenseSkill.getLevel();
        offenseType = offenseSkill.getOffenseType();
        sinType = offenseSkill.getSinType();
        amount = offenseSkill.getAmount();
        skillPower = offenseSkill.getSkillPower();
        coinNumber = offenseSkill.getCoinNumber();
        weight = offenseSkill.getWeight();
        skillEffect = offenseSkill.getSkillEffect();
    }

}
