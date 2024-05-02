package com.limbus.api.domain.skill;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.DefenseType;
import com.limbus.api.domain.type.SinType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

    //코인개수
    private Integer coinNumber;

    @Lob
    private String effect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identity_id")
    private Identity identity;


    @Builder
    public DefenseSkill(String name, Integer level, DefenseType defenseType, SinType sinType, Integer skillPower, Integer coinPower, Integer coinNumber, String effect, Identity identity) {
        this.name = name;
        this.level = level;
        this.defenseType = defenseType;
        this.sinType = sinType;
        this.skillPower = skillPower;
        this.coinPower = coinPower;
        this.coinNumber = coinNumber;
        this.effect = effect;
        setIdentity(identity);
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
        identity.getDefenseSkills().add(this);
    }
}
