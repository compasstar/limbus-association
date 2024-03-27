package com.limbus.api.domain.skill;


import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.CoinType;
import com.limbus.api.domain.type.OffenseType;
import com.limbus.api.domain.type.SinType;
import jakarta.persistence.*;
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
    private OffenseType offenseType;

    //죄악 속성
    private SinType sinType;

    //스킬 수량
    private Integer amount;

    //스킬 위력
    private Integer skillPower;

    //코인 위력
    private Integer coinPower;

    //코인개수
    private Integer coinNumber;

    //플러스코인, 마이너스코인
    private CoinType coinType;

    //가중치
    private Integer weight;

    //코인별 효과
    @Lob
    private String effect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identity_id")
    private Identity identity;

}
