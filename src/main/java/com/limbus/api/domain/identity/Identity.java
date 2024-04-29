package com.limbus.api.domain.identity;

import com.limbus.api.domain.skill.DefenseSkill;
import com.limbus.api.domain.skill.OffenseSkill;
import com.limbus.api.domain.skill.PassiveSkill;
import com.limbus.api.domain.type.Sinner;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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

    //스테이터스 (체력, 속도, 수비레벨)
    //최대레벨, 최대동기화 기준
    @Embedded
    private Status status;

    // 내성정보 (참격내성, 관통내성, 타격내성)
    @Embedded
    private Resistances resistances;

    @Embedded
    private Sanity sanity;

    @OneToMany(mappedBy = "identity")
    private List<OffenseSkill> offenseSkills = new ArrayList<>();

    @OneToMany(mappedBy = "identity")
    private List<DefenseSkill> defenseSkills = new ArrayList<>();

    @OneToMany(mappedBy = "identity")
    private List<PassiveSkill> passiveSkills = new ArrayList<>();

    @Builder
    public Identity(Sinner sinner, String name, Integer rarity, Status status, Resistances resistances, Sanity sanity) {
        this.sinner = sinner;
        this.name = name;
        this.rarity = rarity;
        this.status = status;
        this.resistances = resistances;
        this.sanity = sanity;
    }
}
