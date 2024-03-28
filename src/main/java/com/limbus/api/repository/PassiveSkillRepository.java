package com.limbus.api.repository;

import com.limbus.api.domain.skill.PassiveSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassiveSkillRepository extends JpaRepository<PassiveSkill, Long> {
}
