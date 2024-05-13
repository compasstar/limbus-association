package com.limbus.api.domain.skill;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.PassiveType;
import com.limbus.api.domain.type.SinType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class PassiveSkill {

    @Id @GeneratedValue
    @Column(name = "passive_skill_id")
    private Long id;

    //서포트 패시브
    private Boolean support;

    //이름
    private String name;

    //죄악 속성
    @Enumerated(EnumType.STRING)
    private SinType sinType;
    
    //패시브스킬 타입 (보유, 공명)
    @Enumerated(EnumType.STRING)
    private PassiveType passiveType;
    
    //몇 개 공명, 몇 개 보유
    private Integer amount;

    @ElementCollection
    private List<String> effect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identity_id")
    private Identity identity;


    @Builder
    public PassiveSkill(Boolean support, String name, SinType sinType, PassiveType passiveType, Integer amount, List<String> effect, Identity identity) {
        this.support = support;
        this.name = name;
        this.sinType = sinType;
        this.passiveType = passiveType;
        this.amount = amount;
        this.effect = effect;
        setIdentity(identity);
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
        identity.getPassiveSkills().add(this);
    }
}
