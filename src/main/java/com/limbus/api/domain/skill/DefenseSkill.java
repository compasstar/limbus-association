package com.limbus.api.domain.skill;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.DefenseType;
import com.limbus.api.domain.type.SinType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class DefenseSkill {

    @Id @GeneratedValue
    @Column(name = "defense_skill_id")
    private Long id;

    //스킬 이름
    private String name;

    //수비레벨 (반격의 경우 공격레벨)
    private Integer level;

    // 수비타입 (수비, 반격)
    @Enumerated(EnumType.STRING)
    private DefenseType defenseType;

    //죄악 속성
    @Enumerated(EnumType.STRING)
    private SinType sinType;

    //스킬 위력
    private Integer skillPower;

    //코인 위력
    private Integer coinPower;

    //코인별 효과
    @OneToOne(mappedBy = "defenseSkill")
    private SkillEffect skillEffect;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identity_id")
    private Identity identity;

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }


    @Builder
    public DefenseSkill(String name, Integer level, DefenseType defenseType, SinType sinType, Integer skillPower, Integer coinPower, SkillEffect skillEffect) {
        this.name = name;
        this.level = level;
        this.defenseType = defenseType;
        this.sinType = sinType;
        this.skillPower = skillPower;
        this.coinPower = coinPower;
        addSkillEffect(skillEffect);
    }

    private void addSkillEffect(SkillEffect skillEffect) {
        this.skillEffect = skillEffect;
        skillEffect.setDefenseSkill(this);
    }
}
