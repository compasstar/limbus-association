package com.limbus.api.response.skill;

import com.limbus.api.domain.skill.SkillEffect;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SkillEffectResponse {

    private String effect;
    List<OnHitEffectResponse> onHitEffects = new ArrayList<>();

    public SkillEffectResponse(SkillEffect skillEffect) {
        this.effect = skillEffect.getEffect();

        if (skillEffect.getOnHitEffects() != null) {
            onHitEffects = skillEffect.getOnHitEffects().stream().map(OnHitEffectResponse::new).collect(Collectors.toList());
        }
    }

}
