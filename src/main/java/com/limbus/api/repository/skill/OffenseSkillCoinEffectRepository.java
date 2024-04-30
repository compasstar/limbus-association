package com.limbus.api.repository.skill;

import com.limbus.api.domain.skill.OffenseSkillCoinEffect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffenseSkillCoinEffectRepository extends JpaRepository<OffenseSkillCoinEffect, Long> {

    List<OffenseSkillCoinEffect> findByOffenseSkillId(Long offenseSkillId);

}
