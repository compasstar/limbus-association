package com.limbus.api.response.skill;

import com.limbus.api.domain.type.PassiveType;
import com.limbus.api.domain.type.SinType;
import lombok.Getter;

@Getter
public class PassiveSkillResponse {

    private String name;
    private SinType sinType;
    private PassiveType passiveType;
    private Integer amount;
    private String effect;
}
