package com.limbus.api.response.skill;

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
    private Integer coinPower;
    private Integer coinNumber;
    private Integer weight;
    private SkillEffectResponse skillEffect;


    public OffenseSkillResponse(OffenseSkill offenseSkill) {
        slot = offenseSkill.getSlot();
        name = offenseSkill.getName();
        level = offenseSkill.getLevel();
        offenseType = offenseSkill.getOffenseType();
        sinType = offenseSkill.getSinType();
        amount = offenseSkill.getAmount();
        skillPower = offenseSkill.getSkillPower();
        coinPower = offenseSkill.getCoinPower();
        coinNumber = offenseSkill.getCoinNumber();
        weight = offenseSkill.getWeight();
        skillEffect = new SkillEffectResponse(offenseSkill.getSkillEffect());
    }

}
