package com.limbus.api.domain.identity;

import com.limbus.api.domain.skill.DefenseSkill;
import com.limbus.api.domain.skill.OffenseSkill;
import com.limbus.api.domain.skill.PassiveSkill;
import com.limbus.api.domain.type.Sinner;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Identity {

    @Id @GeneratedValue
    @Column(name = "identity_id")
    private Long id;

    //수감자
    @Enumerated(EnumType.STRING)
    private Sinner sinner;

    private String name;

    //인격등급 (1성, 2성, 3성)
    private Integer rarity;

    //스테이터스 (체력, 속도, 공격레벨, 수비레벨)
    //최대레벨, 최대동기화 기준
    @Embedded
    private Status status;

    // 내성정보 (참격내성, 관통내성, 타격내성)
    @Embedded
    private Resistances resistances;

    @OneToMany(mappedBy = "identity")
    private List<OffenseSkill> offenseSkills = new ArrayList<>();

    @OneToOne(mappedBy = "identity")
    private DefenseSkill defenseSkill;

    @OneToOne(mappedBy = "identity")
    private PassiveSkill passiveSkill;

    @Embedded
    private Sanity sanity;

}
