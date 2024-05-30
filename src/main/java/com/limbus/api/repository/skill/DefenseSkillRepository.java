package com.limbus.api.repository.skill;

import com.limbus.api.domain.skill.DefenseSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefenseSkillRepository extends JpaRepository<DefenseSkill, Long> {

    //수감자 아이디로 검색
    List<DefenseSkill> findByIdentityId(Long identityId);
}
