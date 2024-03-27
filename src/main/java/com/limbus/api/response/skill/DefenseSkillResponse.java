package com.limbus.api.response.skill;

import com.limbus.api.domain.type.CoinType;
import com.limbus.api.domain.type.DefenseType;
import com.limbus.api.domain.type.SinType;
import lombok.Getter;

@Getter
public class DefenseSkillResponse {

    private String name;
    private Integer level;
    private DefenseType defenseType;
    private SinType sinType;
    private Integer skillPower;
    private Integer coinPower;
    private CoinType coinType;
    private String effect;
}
