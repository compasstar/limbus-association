package com.limbus.api.domain.skill;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class OnHitEffect {

    @Id @GeneratedValue
    @Column(name = "on_hit_effect_id")
    private Long id;

    private Integer coin;

    @Lob
    private String effect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private SkillEffect skillEffect;

    public void setSkillEffect(SkillEffect skillEffect) {
        this.skillEffect = skillEffect;
    }

    @Builder
    public OnHitEffect(Integer coin, String effect) {
        this.coin = coin;
        this.effect = effect;
    }
}
