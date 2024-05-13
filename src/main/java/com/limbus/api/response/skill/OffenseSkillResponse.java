package com.limbus.api.response.skill;

import com.limbus.api.domain.skill.OffenseSkill;
import com.limbus.api.domain.skill.OffenseSkillCoinEffect;
import com.limbus.api.domain.type.OffenseType;
import com.limbus.api.domain.type.SinType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OffenseSkillResponse {

    private Integer slot;
    private String name;
    private Integer level;
    private String offenseType;
    private String sinType;
    private Integer amount;
    private Integer skillPower;
    private Integer coinPower;
    private Integer coinNumber;
    private Integer weight;
    private List<String> effect;
    private List<OffenseSkillCoinEffectResponse> offenseSkillCoinEffects = new ArrayList<>();

    public OffenseSkillResponse(OffenseSkill offenseSkill) {
        slot = offenseSkill.getSlot();
        name = offenseSkill.getName();
        level = offenseSkill.getLevel();
        offenseType = offenseSkill.getOffenseType() != null ? offenseSkill.getOffenseType().getName() : null;
        sinType = offenseSkill.getSinType() != null ? offenseSkill.getSinType().getName() : null;
        amount = offenseSkill.getAmount();
        skillPower = offenseSkill.getSkillPower();
        coinPower = offenseSkill.getCoinPower();
        coinNumber = offenseSkill.getCoinNumber();
        weight = offenseSkill.getWeight();
        effect = offenseSkill.getEffect();
        offenseSkill.getOffenseSkillCoinEffects().forEach(
                offenseSkillCoinEffect -> offenseSkillCoinEffects.add(new OffenseSkillCoinEffectResponse(offenseSkillCoinEffect))
        );
    }

}
