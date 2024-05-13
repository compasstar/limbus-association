package com.limbus.api.response.skill;

import com.limbus.api.domain.skill.OffenseSkillCoinEffect;
import lombok.Getter;

import java.util.List;

@Getter
public class OffenseSkillCoinEffectResponse {

    private Integer coin;
    private List<String> effect;

    public OffenseSkillCoinEffectResponse(OffenseSkillCoinEffect offenseSkillCoinEffect) {
        this.coin = offenseSkillCoinEffect.getCoin();
        this.effect = offenseSkillCoinEffect.getEffect();
    }

}
