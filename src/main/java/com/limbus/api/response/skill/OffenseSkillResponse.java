package com.limbus.api.response.skill;

import com.limbus.api.domain.type.CoinType;
import com.limbus.api.domain.type.OffenseType;
import com.limbus.api.domain.type.SinType;
import lombok.Getter;

@Getter
public class OffenseSkillResponse {

    private Integer slot;
    private String name;
    private Integer level;
    private OffenseType offenseType;
    private SinType sinType;
    private Integer amount;
    private Integer skillPower;
    private Integer coinNumber;
    private CoinType coinType;
    private Integer weight;
    private String effect;

}
