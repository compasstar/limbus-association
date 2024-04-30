package com.limbus.api.repository.skill;

import com.limbus.api.BeforeTest;
import com.limbus.api.domain.skill.DefenseSkill;
import com.limbus.api.domain.skill.OffenseSkill;
import com.limbus.api.domain.type.SinType;
import com.limbus.api.repository.identity.IdentityRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class DefenseSkillRepositoryTest {

    @Autowired
    DefenseSkillRepository defenseSkillRepository;

    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    BeforeTest beforeTest = new BeforeTest();

    @BeforeEach
    void before() {
        beforeTest.saveIdentities();
    }

    @Test
    @DisplayName("findByIdentityId 테스트")
    void findByIdentityIdTest() {
        //when
        Long identityId = identityRepository.findByName("피쿼드호 선장").get(0).getId();
        List<DefenseSkill> defenseSkills = defenseSkillRepository.findByIdentityId(identityId);
        DefenseSkill defenseSkill = defenseSkills.get(0);

        //then
        assertEquals(1, defenseSkills.size());
        assertEquals("공포를 날려주지", defenseSkill.getName());
        assertEquals(37, defenseSkill.getLevel());
        assertEquals(SinType.PRIDE, defenseSkill.getSinType());
        assertEquals(10, defenseSkill.getSkillPower());
        assertEquals(4, defenseSkill.getCoinPower());
        assertEquals("[사용시] 조작 패널에서 자신의 양 옆에 위치한 아군의 정신력 5 회복", defenseSkill.getEffect());
    }

}