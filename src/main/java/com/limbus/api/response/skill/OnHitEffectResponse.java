package com.limbus.api.response.skill;

import com.limbus.api.domain.skill.OnHitEffect;
import lombok.Getter;

@Getter
public class OnHitEffectResponse {

    private Integer coin;
    private String effect;

    public OnHitEffectResponse(OnHitEffect onHitEffect) {
        this.coin = onHitEffect.getCoin();
        this.effect = onHitEffect.getEffect();
    }

}
