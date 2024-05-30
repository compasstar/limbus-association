package com.limbus.api.repository.skill;

import com.limbus.api.domain.skill.PassiveSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassiveSkillRepository extends JpaRepository<PassiveSkill, Long> {

    List<PassiveSkill> findByIdentityId(Long identityId);
    List<PassiveSkill> findByIdentityIdAndSupport(Long identityId, boolean support);
}
