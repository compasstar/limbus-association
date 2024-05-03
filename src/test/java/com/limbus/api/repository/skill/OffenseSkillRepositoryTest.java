package com.limbus.api.repository.skill;

import com.limbus.api.BeforeTest;
import com.limbus.api.domain.skill.OffenseSkill;
import com.limbus.api.domain.type.OffenseType;
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
class OffenseSkillRepositoryTest {

    @Autowired
    OffenseSkillRepository offenseSkillRepository;

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
        Long identityId = identityRepository.findByEnglishName("The_Pequod_Captain_Ishmael").orElseThrow(IllegalArgumentException::new).getId();
        List<OffenseSkill> offenseSkills = offenseSkillRepository.findByIdentityId(identityId);

        //then
        assertEquals(3, offenseSkills.size());
    }

    @Test
    @DisplayName("findByIdentityIdAndSlot 스킬1 테스트")
    void findByIdentityIdAndSlotTest1() {
        //when
        Long identityId = identityRepository.findByEnglishName("The_Pequod_Captain_Ishmael")
                .orElseThrow(IllegalArgumentException::new).getId();
        OffenseSkill offenseSkill1 = offenseSkillRepository.findByIdentityIdAndSlot(identityId, 1)
                .orElseThrow(() -> new IllegalArgumentException("스킬1 슬롯이 존재하지 않습니다."));

        //then
        assertEquals(1, offenseSkill1.getSlot());
        assertEquals("내 주위에 서라!", offenseSkill1.getName());
        assertEquals(43, offenseSkill1.getLevel());
        assertEquals(OffenseType.PIERCE, offenseSkill1.getOffenseType());
        assertEquals(SinType.ENVY, offenseSkill1.getSinType());
        assertEquals(3, offenseSkill1.getAmount());
        assertEquals(4, offenseSkill1.getSkillPower());
        assertEquals(4, offenseSkill1.getCoinPower());
        assertEquals(2, offenseSkill1.getCoinNumber());
        assertEquals(1, offenseSkill1.getWeight());
        assertEquals("[전투 시작시] 조작 패널에서 자신의 양 옆의 아군에게 방어 레벨 증가 2 부여\n[사용시] 다음 턴에 최대 체력이 가장 높은 아군의 왼쪽 슬롯의 도발치가 (가장 높은 공명 수 )만큼 증가 (턴 당 1회)\n- 가장 높은 완전 공명의 합이 4이상이면, 효과 발동 시 보호 1 부여", offenseSkill1.getEffect());

        assertEquals(1, offenseSkill1.getOffenseSkillCoinEffects().size());
    }

    @Test
    @DisplayName("findByIdentityIdAndSlot 스킬2 테스트")
    void findByIdentityIdAndSlotTest2() {
        //when
        Long identityId = identityRepository.findByEnglishName("The_Pequod_Captain_Ishmael")
                .orElseThrow(IllegalArgumentException::new).getId();
        OffenseSkill offenseSkill2 = offenseSkillRepository.findByIdentityIdAndSlot(identityId, 2)
                .orElseThrow(() -> new IllegalArgumentException("스킬2 슬롯이 존재하지 않습니다."));

        //then
        assertEquals(2, offenseSkill2.getSlot());
        assertEquals("끝까지 추적한다!", offenseSkill2.getName());
        assertEquals(43, offenseSkill2.getLevel());
        assertEquals(OffenseType.PIERCE, offenseSkill2.getOffenseType());
        assertEquals(SinType.PRIDE, offenseSkill2.getSinType());
        assertEquals(2, offenseSkill2.getAmount());
        assertEquals(4, offenseSkill2.getSkillPower());
        assertEquals(4, offenseSkill2.getCoinPower());
        assertEquals(3, offenseSkill2.getCoinNumber());
        assertEquals(1, offenseSkill2.getWeight());
        assertEquals("[전투 시작시] 조작 패널에서 자신의 양 옆의 아군에게 공격 레벨 증가 3 부여\n[사용시] 가장 높은 공명의 공명당 20% 확률로 조작 패널에서 자신의 우측에 위치한 아군에게 이번 턴에 원호 공격을 명령함\n- 가장 높은 완전 공명의 합이 4 이상이면, 대상에게 피해량 증가 2 부여\n- 오만 완전 공명의 합이 4 이상이면, 오만 위력 증가 2 추가 부여", offenseSkill2.getEffect());

        assertEquals(2, offenseSkill2.getOffenseSkillCoinEffects().size());
    }

    @Test
    @DisplayName("findByIdentityIdAndSlot 스킬3 테스트")
    void findByIdentityIdAndSlotTest3() {
        //when
        Long identityId = identityRepository.findByEnglishName("The_Pequod_Captain_Ishmael")
                .orElseThrow(IllegalArgumentException::new).getId();
        OffenseSkill offenseSkill3 = offenseSkillRepository.findByIdentityIdAndSlot(identityId, 3)
                .orElseThrow(() -> new IllegalArgumentException("스킬3 슬롯이 존재하지 않습니다."));

        //then
        assertEquals(3, offenseSkill3.getSlot());
        assertEquals("집착의 작살", offenseSkill3.getName());
        assertEquals(43, offenseSkill3.getLevel());
        assertEquals(OffenseType.PIERCE, offenseSkill3.getOffenseType());
        assertEquals(SinType.WRATH, offenseSkill3.getSinType());
        assertEquals(1, offenseSkill3.getAmount());
        assertEquals(4, offenseSkill3.getSkillPower());
        assertEquals(3, offenseSkill3.getCoinPower());
        assertEquals(4, offenseSkill3.getCoinNumber());
        assertEquals(1, offenseSkill3.getWeight());
        assertEquals("대상의 잃은 체력 1%당 피해랑 + 0.3% (최대 30%)\n대상의 출혈 5당 코인 위력 + 1 (최대 2)\n[공격 종료시] 적이 흐트러짐이 되었거나 사망했으면, 정신력이 가장 낮은 아군 1 + (가장 높은 완전 공명 수/2)명의 정신력 10 회복, 호흡 2, 호흡 횟수 4 부여 (최대 4회. 최대 회복 인원 수: 4명)", offenseSkill3.getEffect());

        assertEquals(3, offenseSkill3.getOffenseSkillCoinEffects().size());
    }

}