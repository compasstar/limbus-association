package com.limbus.api.domain.skill;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.PassiveType;
import com.limbus.api.domain.type.SinType;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class PassiveSkill {

    @Id @GeneratedValue
    @Column(name = "passive_skill_id")
    private Long id;

    private String name;

    //죄악 속성
    @Enumerated(EnumType.STRING)
    private SinType sinType;
    
    //패시브스킬 타입 (보유, 공명)
    @Enumerated(EnumType.STRING)
    private PassiveType passiveType;
    
    //몇 개 공명, 몇 개 보유
    private Integer amount;
    
    //효과
    @Lob
    private String effect;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identity_id")
    private Identity identity;

}
