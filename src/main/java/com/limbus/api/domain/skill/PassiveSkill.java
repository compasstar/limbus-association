package com.limbus.api.domain.skill;

import com.limbus.api.domain.type.PassiveSkillType;
import com.limbus.api.domain.type.SinType;
import jakarta.persistence.Embeddable;

@Embeddable
public class PassiveSkill {

    //죄악 속성
    private SinType sinType;
    
    //패시브스킬 타입 (보유, 공명)
    private PassiveSkillType passiveSkillType;
    
    //몇 개 공명, 몇 개 보유
    private Integer amount;
    
    //효과
    private String effect;

}