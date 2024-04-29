package com.limbus.api.response.skill;

import com.limbus.api.domain.skill.OffenseSkillCoinEffect;
import lombok.Getter;

@Getter
public class OffenseSkillCoinEffectResponse {

    private Integer coin;
    private String effect;

    public OffenseSkillCoinEffectResponse(OffenseSkillCoinEffect offenseSkillCoinEffect) {
        this.coin = offenseSkillCoinEffect.getCoin();
        this.effect = offenseSkillCoinEffect.getEffect();
    }

}
