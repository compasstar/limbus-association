package com.limbus.api.service;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.identity.Resistances;
import com.limbus.api.domain.identity.Sanity;
import com.limbus.api.domain.identity.Status;
import com.limbus.api.domain.skill.*;
import com.limbus.api.domain.type.*;
import com.limbus.api.repository.*;
import com.limbus.api.response.IdentityResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class IdentityServiceTest {

    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    OffenseSkillRepository offenseSkillRepository;

    @Autowired
    DefenseSkillRepository defenseSkillRepository;

    @Autowired
    PassiveSkillRepository passiveSkillRepository;

    @Autowired
    SkillEffectRepository skillEffectRepository;

    @Autowired
    OnHitEffectRepository onHitEffectRepository;

    @Autowired
    IdentityService identityService;

    @BeforeEach
    void save_피쿼드호_선장() {
        /**
         * 스테이터스 & 내성정보
         */
        Status status = Status.builder()
                .hp(160)
                .speed("4-7")
                .defenseLevel(37)
                .build();
        Resistances resistances = Resistances.builder()
                .slashResistance(1.0)
                .pierceResistance(0.5)
                .bluntResistance(2.0)
                .build();

        /**
         * 공격스킬 1, 2, 3
         */
        SkillEffect skillEffect1 = SkillEffect.builder()
                .effect("[전투 시작시] 조작 패널에서 자신의 양 옆의 아군에게 방어 레벨 증가 2 부여\n[사용시] 다음 턴에 최대 체력이 가장 높은 아군의 왼쪽 슬롯의 도발치가 (가장 높은 공명 수 )만큼 증가 (턴 당 1회)\n- 가장 높은 완전 공명의 합이 4이상이면, 효과 발동 시 보호 1 부여")
                .onHitEffects(List.of(OnHitEffect.builder().coin(2).effect("[적중시] 출혈 2 부여").build()))
                .build();

        skillEffectRepository.save(skillEffect1);

        OffenseSkill offenseSkill1 = OffenseSkill.builder()
                .slot(1)
                .name("내 주위에 서리!")
                .level(43)
                .offenseType(OffenseType.PIERCE)
                .sinType(SinType.ENVY)
                .amount(3)
                .skillPower(4)
                .coinPower(4)
                .coinNumber(2)
                .weight(1)
                .skillEffect(skillEffect1)
                .build();
        offenseSkillRepository.save(offenseSkill1);

        SkillEffect skillEffect2 = SkillEffect.builder()
                .effect("[전투 시작시] 조작 패널에서 자신의 양 옆의 아군에게 공격 레벨 증가 3 부여\n[사용시] 가장 높은 공명의 공명당 20% 확률로 조작 패널에서 자신의 우측에 위치한 아군에게 이번 턴에 원호 공격을 명령함\n- 가장 높은 완전 공명의 합이 4 이상이면, 대상에게 피해량 증가 2 부여\n- 오만 완전 공명의 합이 4 이상이면, 오만 위력 증가 2 추가 부여")
                .onHitEffects(List.of(
                                OnHitEffect.builder().coin(2).effect("[적중시] 출혈 2 부여").build(),
                                OnHitEffect.builder().coin(3).effect("[적중시] 화상 1 부여").build()
                        )
                )
                .build();

        skillEffectRepository.save(skillEffect2);

        OffenseSkill offenseSkill2 = OffenseSkill.builder()
                .slot(2)
                .name("끝까지 추적한다!")
                .level(43)
                .offenseType(OffenseType.PIERCE)
                .sinType(SinType.PRIDE)
                .amount(2)
                .skillPower(4)
                .coinPower(4)
                .coinNumber(3)
                .weight(1)
                .skillEffect(skillEffect2)
                .build();

        offenseSkillRepository.save(offenseSkill2);

        SkillEffect skillEffect3 = SkillEffect.builder()
                .effect("대상의 잃은 체력 1%당 피해랑 + 0.3% (최대 30%)\n대상의 출혈 5당 코인 위력 + 1 (최대 2)\n[공격 종료시] 적이 흐트러짐이 되었거나 사망했으면, 정신력이 가장 낮은 아군 1 + (가장 높은 완전 공명 수/2)명의 정신력 10 회복, 호흡 2, 호흡 횟수 4 부여 (최대 4회. 최대 회복 인원 수: 4명)")
                .onHitEffects(List.of(
                                OnHitEffect.builder().coin(2).effect("[적중시] 출혈 횟수 1 증가").build(),
                                OnHitEffect.builder().coin(3).effect("[적중시] 출혈 2 부여").build(),
                                OnHitEffect.builder().coin(4).effect("[적중시] 화상 횟수 1 증가").build()
                        )
                )
                .build();

        skillEffectRepository.save(skillEffect3);

        OffenseSkill offenseSkill3 = OffenseSkill.builder()
                .slot(3)
                .name("집착의 작살")
                .level(43)
                .offenseType(OffenseType.PIERCE)
                .sinType(SinType.WRATH)
                .amount(1)
                .skillPower(4)
                .coinPower(3)
                .coinNumber(4)
                .weight(1)
                .skillEffect(skillEffect3)
                .build();

        offenseSkillRepository.save(offenseSkill3);

        /**
         * 수비
         */
        SkillEffect skillEffectDefense = SkillEffect.builder()
                .effect("[사용시] 조작 패널에서 자신의 양 옆에 위치한 아군의 정신력 5 회복")
                .onHitEffects(new ArrayList<>())
                .build();

        skillEffectRepository.save(skillEffectDefense);

        DefenseSkill defenseSkill = DefenseSkill.builder()
                .name("공포를 날려주지")
                .level(37)
                .defenseType(DefenseType.DEFENSE)
                .sinType(SinType.PRIDE)
                .skillPower(10)
                .coinPower(4)
                .skillEffect(skillEffectDefense)
                .build();
        defenseSkillRepository.save(defenseSkill);

        /**
         * 패시브
         */
        SkillEffect skillEffectPassive1 = SkillEffect.builder()
                .effect("자신의 공격 종료 시에 대상이 사망했으면,\n- 호흡 4, 호흡 횟수를 2 얻고, 다음 턴에 과열된 가스 작살 상태가 됨\n- 자신을 제외한 정신력이 가장 낮은 아군 2명의 정신력 7 회복, 호흡 4 부여\n- 대상이 피쿼드호 선원이면, 정신력 5 추가 회복, 다음 턴에 관통 위력 증가 1 부여")
                .onHitEffects(new ArrayList<>())
                .build();
        skillEffectRepository.save(skillEffectPassive1);

        PassiveSkill passiveSkill1 = PassiveSkill.builder()
                .support(false)
                .name("피쿼드호의 선장")
                .sinType(SinType.PRIDE)
                .passiveType(PassiveType.RESONANCE)
                .amount(3)
                .skillEffect(skillEffectPassive1)
                .build();
        passiveSkillRepository.save(passiveSkill1);

        SkillEffect skillEffectPassive2 = SkillEffect.builder()
                .effect("대상이 <호수의 존재> 면 피해량 +10%")
                .onHitEffects(new ArrayList<>())
                .build();
        skillEffectRepository.save(skillEffectPassive2);

        PassiveSkill passiveSkill2 = PassiveSkill.builder()
                .support(false)
                .name("사냥 시간")
                .skillEffect(skillEffectPassive2)
                .build();
        passiveSkillRepository.save(passiveSkill2);

        SkillEffect skillEffectPassive3 = SkillEffect.builder()
                .effect("정신력이 가장 높은 아군 1명이 자신의 공격 종료 시 대상이 사망했으면, 호흡 2, 호흡 회수 2 얻음(턴 당 2회)")
                .onHitEffects(new ArrayList<>())
                .build();
        skillEffectRepository.save(skillEffectPassive3);

        PassiveSkill passiveSkill3 = PassiveSkill.builder()
                .support(true)
                .name("선장의 명령")
                .sinType(SinType.PRIDE)
                .passiveType(PassiveType.RESONANCE)
                .amount(3)
                .skillEffect(skillEffectPassive3)
                .build();
        passiveSkillRepository.save(passiveSkill3);

        /**
         * 정신력
         */
        Sanity sanity = Sanity.builder()
                .panic("한 턴 동안 행동하지 않음")
                .factorsIncreasingSanity("합 승리 시 합 횟수에 비례하여 증가\n(증가량: 기본 값 10, 2합부터 1합당 20%식 증가)\n적 처치 시 처치한 적의 레벨에 관계 없이 10 증가\n적 처치 시 처치한 적의 레벨이 자신의 레벨 이상인 경우 15 증가\n아군 사망 시 사망한 아군의 레벨에 관계 없이 10 증가")
                .factorsDecreasingSanity("아군이 처치한 적의 레벨이 자신의 레벨 이상인 경우 5 감소. 원호 공격이 부여된 아군이 처치한 경우에는 감소하지 않음.")
                .build();

        /**
         * 인격 생성
         */
        Identity identity = Identity.builder()
                .sinner(Sinner.ISHMAEL)
                .name("피쿼드호 선장")
                .rarity(3)
                .status(status)
                .resistances(resistances)
                .offenseSkills(List.of(offenseSkill1, offenseSkill2, offenseSkill3))
                .defenseSkill(defenseSkill)
                .passiveSkills(List.of(passiveSkill1, passiveSkill2, passiveSkill3))
                .sanity(sanity)
                .build();

        identityRepository.save(identity);
    }


    @Test
    @DisplayName("인격 1개 조회")
    void getIdentityTest() {
        //given
        //BeforeEach -> save_피쿼드호_선장

        //when
        Identity identity = identityRepository.findByName("피쿼드호 선장").get(0);
        IdentityResponse identityResponse = identityService.getIdentity(identity.getId());

        //then
        assertNotNull(identityResponse);
        assertEquals(1, identityRepository.count());
        assertEquals(160, identityResponse.getStatus().getHp());
        assertEquals(3, identityResponse.getOffenseSkills().get(0).getAmount());
        assertEquals("대상이 <호수의 존재> 면 피해량 +10%", identityResponse.getPassiveSkills().get(1).getSkillEffect().getEffect());

    }

}