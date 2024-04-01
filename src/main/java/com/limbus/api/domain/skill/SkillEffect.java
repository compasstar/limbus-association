package com.limbus.api.domain.skill;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class SkillEffect {

    @Id @GeneratedValue
    @Column(name = "skill_effect_id")
    private Long id;

    @Lob
    private String effect;

    @OneToMany(mappedBy = "skillEffect")
    private List<OnHitEffect> onHitEffects = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offense_skill_id")
    private OffenseSkill offenseSkill;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "defense_skill_id")
    private DefenseSkill defenseSkill;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passive_skill_id")
    private PassiveSkill passiveSkill;

    public void setOffenseSkill(OffenseSkill offenseSkill) {
        this.offenseSkill = offenseSkill;
    }

    public void setDefenseSkill(DefenseSkill defenseSkill) {
        this.defenseSkill = defenseSkill;
    }

    public void setPassiveSkill(PassiveSkill passiveSkill) {
        this.passiveSkill = passiveSkill;
    }

    @Builder
    public SkillEffect(String effect, List<OnHitEffect> onHitEffects) {
        this.effect = effect;

        if (onHitEffects != null) {
            for (OnHitEffect onHitEffect : onHitEffects) {
                addOnHitEffect(onHitEffect);
            }
        }

        this.onHitEffects = onHitEffects;
    }

    private void addOnHitEffect(OnHitEffect onHitEffect) {
        onHitEffects.add(onHitEffect);
        onHitEffect.setSkillEffect(this);
    }

}
