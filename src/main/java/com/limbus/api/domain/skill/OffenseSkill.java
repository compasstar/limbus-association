package com.limbus.api.domain.skill;


import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.OffenseType;
import com.limbus.api.domain.type.SinType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Entity
@NoArgsConstructor
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

    @Lob
    private String effect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identity_id")
    private Identity identity;

    @OneToMany(mappedBy = "offenseSkill")
    private List<OffenseSkillCoinEffect> offenseSkillCoinEffects = new ArrayList<>();

    @Builder
    public OffenseSkill(Integer slot, String name, Integer level, OffenseType offenseType, SinType sinType,
                        Integer amount, Integer skillPower, Integer coinPower, Integer coinNumber, Integer weight,
                        String effect, Identity identity) {
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
        this.effect = effect;
        setIdentity(identity);
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
        identity.getOffenseSkills().add(this);
    }
}
