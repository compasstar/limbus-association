package com.limbus.api.domain.skill;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class OffenseSkillCoinEffect {

    @Id @GeneratedValue
    @Column(name = "offense_skill_coin_effect_id")
    private Long id;

    private Integer coin;

    @ElementCollection
    private List<String> effect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offense_skill_id")
    private OffenseSkill offenseSkill;

    @Builder
    public OffenseSkillCoinEffect(Integer coin, List<String> effect, OffenseSkill offenseSkill) {
        this.coin = coin;
        this.effect = effect;
        setOffenseSkill(offenseSkill);
    }

    private void setOffenseSkill(OffenseSkill offenseSkill) {
        this.offenseSkill = offenseSkill;
        offenseSkill.getOffenseSkillCoinEffects().add(this);
    }
}
