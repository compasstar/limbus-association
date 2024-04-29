package com.limbus.api.repository.skill;

import com.limbus.api.domain.skill.OffenseSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OffenseSkillRepository extends JpaRepository<OffenseSkill, Long> {

    //수감자 아이디로 검색
    List<OffenseSkill> findByIdentityId(Long identityId);

    //수감자 아이디로 검색하고 그 중 스킬슬롯으로 검색
    Optional<OffenseSkill> findByIdentityIdAndSlot(Long identityId, int slot);
}
