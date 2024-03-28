package com.limbus.api.domain.skill;


import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.OffenseType;
import com.limbus.api.domain.type.SinType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class OffenseSkill {

    @Id @GeneratedValue
    @Column(name = "offense_skill_id")
    private Long id;

    //1스, 2스, 3스
    private Integer slot;

    //스킬 이름
    private String name;

    //공격레벨
    private Integer level;

    //공격 유형
    @Enumerated(EnumType.STRING)
    private OffenseType offenseType;

    //죄악 속성
    @Enumerated(EnumType.STRING)
    private SinType sinType;

    //스킬 수량
    private Integer amount;

    //스킬 위력
    private Integer skillPower;

    //코인 위력
    private Integer coinPower;

    //코인개수
    private Integer coinNumber;

    //가중치
    private Integer weight;

    //코인별 효과
    @OneToOne(mappedBy = "offenseSkill")
    private SkillEffect skillEffect;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identity_id")
    private Identity identity;

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }


    @Builder
    public OffenseSkill(Integer slot, String name, Integer level, OffenseType offenseType, SinType sinType, Integer amount, Integer skillPower, Integer coinPower, Integer coinNumber, Integer weight, SkillEffect skillEffect) {
        this.slot = slot;
        this.name = name;
        this.level = level;
        this.offenseType = offenseType;
        this.sinType = sinType;
        this.amount = amount;
        this.skillPower = skillPower;
        this.coinPower = coinPower;
        this.coinNumber = coinNumber;
        this.weight = weight;
        addSkillEffect(skillEffect);
    }

    private void addSkillEffect(SkillEffect skillEffect) {
        this.skillEffect = skillEffect;
        skillEffect.setOffenseSkill(this);
    }
}
