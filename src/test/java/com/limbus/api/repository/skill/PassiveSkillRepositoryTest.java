package com.limbus.api.repository.skill;

import com.limbus.api.BeforeTest;
import com.limbus.api.domain.skill.PassiveSkill;
import com.limbus.api.domain.type.PassiveType;
import com.limbus.api.domain.type.SinType;
import com.limbus.api.repository.identity.IdentityRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
class PassiveSkillRepositoryTest {

    @Autowired
    PassiveSkillRepository passiveSkillRepository;

    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    BeforeTest beforeTest = new BeforeTest();

    @BeforeEach
    void before() {
        beforeTest.saveIdentities();
    }

    @Test
    @DisplayName("findByIdentity 테스트")
    void findByIdentityTest() {
        //when
        Long identityId = identityRepository.findByEnglishName("The_Pequod_Captain_Ishmael")
                .orElseThrow(IllegalArgumentException::new).getId();
        List<PassiveSkill> passiveSkills = passiveSkillRepository.findByIdentityId(identityId);
        PassiveSkill passiveSkill1 = passiveSkills.get(0);
        PassiveSkill passiveSkill2 = passiveSkills.get(1);
        PassiveSkill passiveSkill3 = passiveSkills.get(2);

        //then
        assertEquals(3, passiveSkills.size());

        assertEquals(false, passiveSkill1.getSupport());
        assertEquals("피쿼드호의 선장", passiveSkill1.getName());
        assertEquals(SinType.PRIDE, passiveSkill1.getSinType());
        assertEquals(PassiveType.RESONANCE, passiveSkill1.getPassiveType());
        assertEquals(3, passiveSkill1.getAmount());
        assertEquals("자신의 공격 종료 시에 대상이 사망했으면,\n- 호흡 4, 호흡 횟수를 2 얻고, 다음 턴에 과열된 가스 작살 상태가 됨\n- 자신을 제외한 정신력이 가장 낮은 아군 2명의 정신력 7 회복, 호흡 4 부여\n- 대상이 피쿼드호 선원이면, 정신력 5 추가 회복, 다음 턴에 관통 위력 증가 1 부여", passiveSkill1.getEffect());

        assertEquals(false, passiveSkill2.getSupport());
        assertEquals("사냥 시간", passiveSkill2.getName());
        assertEquals("대상이 <호수의 존재> 면 피해량 +10%", passiveSkill2.getEffect());

        assertEquals(true, passiveSkill3.getSupport());
        assertEquals("선장의 명령", passiveSkill3.getName());
        assertEquals(SinType.PRIDE, passiveSkill3.getSinType());
        assertEquals(PassiveType.RESONANCE, passiveSkill3.getPassiveType());
        assertEquals(3, passiveSkill3.getAmount());
        assertEquals("정신력이 가장 높은 아군 1명이 자신의 공격 종료 시 대상이 사망했으면, 호흡 2, 호흡 횟수 2 얻음(턴 당 2회)", passiveSkill3.getEffect());
    }

    @Test
    @DisplayName("findByIdentityAndSupport 테스트")
    void findByIdentityAndSupportTest() {
        //when
        Long identityId = identityRepository.findByEnglishName("The_Pequod_Captain_Ishmael").orElseThrow(IllegalArgumentException::new).getId();
        List<PassiveSkill> passiveSkillsNotSupport = passiveSkillRepository.findByIdentityIdAndSupport(identityId, false);
        List<PassiveSkill> passiveSkillsSupport = passiveSkillRepository.findByIdentityIdAndSupport(identityId, true);

        //then
        assertEquals(2, passiveSkillsNotSupport.size());
        assertEquals("피쿼드호의 선장", passiveSkillsNotSupport.get(0).getName());
        assertEquals("사냥 시간", passiveSkillsNotSupport.get(1).getName());

        assertEquals(1, passiveSkillsSupport.size());
        assertEquals("선장의 명령", passiveSkillsSupport.get(0).getName());
    }

}