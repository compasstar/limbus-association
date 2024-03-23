package com.limbus.api.domain.skill;

import com.limbus.api.domain.type.CoinType;
import com.limbus.api.domain.type.DefenseType;
import com.limbus.api.domain.type.SinType;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class DefenseSkill {

    //스킬 이름
    private String name;

    //수비레벨 (반격의 경우 공격레벨)
    private Integer level;

    // 수비타입 (수비, 반격)
    private DefenseType defenseType;

    //죄악 속성
    private SinType sinType;

    //스킬 위력
    private Integer skillPower;

    //코인 위력
    private Integer coinPower;

    //코인 타입
    private CoinType coinType;

    //코인별 효과
    private String effect;


}
