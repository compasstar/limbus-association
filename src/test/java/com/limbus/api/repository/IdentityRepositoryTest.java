package com.limbus.api.repository;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.identity.Resistances;
import com.limbus.api.domain.identity.Sanity;
import com.limbus.api.domain.identity.Status;
import com.limbus.api.domain.skill.*;
import com.limbus.api.domain.type.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class IdentityRepositoryTest {

    @Autowired
    EntityManager em;

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

    @BeforeEach
    void saveIdentities() {
        save_피쿼드호_선장();
        save_R사_제_4무리_순록팀();
    }

    void save_피쿼드호_선장() {

        /**
         * 1. save OnHitEffect
         */
        OnHitEffect onHitEffect1Coin2 = OnHitEffect.builder()
                .coin(2)
                .effect("[적중시] 출혈 2 부여")
                .build();
        OnHitEffect onHitEffect2Coin2 = OnHitEffect.builder()
                .coin(2)
                .effect("[적중시] 출혈 2 부여")
                .build();
        OnHitEffect onHitEffect2Coin3 = OnHitEffect.builder()
                .coin(3)
                .effect("[적중시] 화상 1 부여")
                .build();
        OnHitEffect onHitEffect3Coin2 = OnHitEffect.builder()
                .coin(2)
                .effect("[적중시] 출혈 횟수 1 증가")
                .build();
        OnHitEffect onHitEffect3Coin3 = OnHitEffect.builder()
                .coin(3)
                .effect("[적중시] 출혈 2 부여")
                .build();
        OnHitEffect onHitEffect3Coin4 = OnHitEffect.builder()
                .coin(4)
                .effect("[적중시] 화상 횟수 1 증가")
                .build();

        onHitEffectRepository.save(onHitEffect1Coin2);
        onHitEffectRepository.save(onHitEffect2Coin2);
        onHitEffectRepository.save(onHitEffect2Coin3);
        onHitEffectRepository.save(onHitEffect3Coin2);
        onHitEffectRepository.save(onHitEffect3Coin3);
        onHitEffectRepository.save(onHitEffect3Coin4);

        /**
         * 2. save SkillEffect
         */
        SkillEffect skillEffect1 = SkillEffect.builder()
                .effect("[전투 시작시] 조작 패널에서 자신의 양 옆의 아군에게 방어 레벨 증가 2 부여\n[사용시] 다음 턴에 최대 체력이 가장 높은 아군의 왼쪽 슬롯의 도발치가 (가장 높은 공명 수 )만큼 증가 (턴 당 1회)\n- 가장 높은 완전 공명의 합이 4이상이면, 효과 발동 시 보호 1 부여")
                .onHitEffects(List.of(onHitEffect1Coin2))
                .build();

        SkillEffect skillEffect2 = SkillEffect.builder()
                .effect("[전투 시작시] 조작 패널에서 자신의 양 옆의 아군에게 공격 레벨 증가 3 부여\n[사용시] 가장 높은 공명의 공명당 20% 확률로 조작 패널에서 자신의 우측에 위치한 아군에게 이번 턴에 원호 공격을 명령함\n- 가장 높은 완전 공명의 합이 4 이상이면, 대상에게 피해량 증가 2 부여\n- 오만 완전 공명의 합이 4 이상이면, 오만 위력 증가 2 추가 부여")
                .onHitEffects(List.of(onHitEffect2Coin2, onHitEffect2Coin3))
                .build();

        SkillEffect skillEffect3 = SkillEffect.builder()
                .effect("대상의 잃은 체력 1%당 피해랑 + 0.3% (최대 30%)\n대상의 출혈 5당 코인 위력 + 1 (최대 2)\n[공격 종료시] 적이 흐트러짐이 되었거나 사망했으면, 정신력이 가장 낮은 아군 1 + (가장 높은 완전 공명 수/2)명의 정신력 10 회복, 호흡 2, 호흡 횟수 4 부여 (최대 4회. 최대 회복 인원 수: 4명)")
                .onHitEffects(List.of(onHitEffect3Coin2, onHitEffect3Coin3, onHitEffect3Coin4))
                .build();

        SkillEffect skillEffectDefense = SkillEffect.builder()
                .effect("[사용시] 조작 패널에서 자신의 양 옆에 위치한 아군의 정신력 5 회복")
                .onHitEffects(new ArrayList<>())
                .build();

        SkillEffect skillEffectPassive1 = SkillEffect.builder()
                .effect("자신의 공격 종료 시에 대상이 사망했으면,\n- 호흡 4, 호흡 횟수를 2 얻고, 다음 턴에 과열된 가스 작살 상태가 됨\n- 자신을 제외한 정신력이 가장 낮은 아군 2명의 정신력 7 회복, 호흡 4 부여\n- 대상이 피쿼드호 선원이면, 정신력 5 추가 회복, 다음 턴에 관통 위력 증가 1 부여")
                .onHitEffects(new ArrayList<>())
                .build();

        SkillEffect skillEffectPassive2 = SkillEffect.builder()
                .effect("대상이 <호수의 존재> 면 피해량 +10%")
                .onHitEffects(new ArrayList<>())
                .build();

        SkillEffect skillEffectPassive3 = SkillEffect.builder()
                .effect("정신력이 가장 높은 아군 1명이 자신의 공격 종료 시 대상이 사망했으면, 호흡 2, 호흡 회수 2 얻음(턴 당 2회)")
                .onHitEffects(new ArrayList<>())
                .build();

        skillEffectRepository.save(skillEffect1);
        skillEffectRepository.save(skillEffect2);
        skillEffectRepository.save(skillEffect3);
        skillEffectRepository.save(skillEffectDefense);
        skillEffectRepository.save(skillEffectPassive1);
        skillEffectRepository.save(skillEffectPassive2);
        skillEffectRepository.save(skillEffectPassive3);

        /**
         * 3. save OffenseSkill
         */
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

        offenseSkillRepository.save(offenseSkill1);
        offenseSkillRepository.save(offenseSkill2);
        offenseSkillRepository.save(offenseSkill3);

        /**
         * 4. save DefenseSkill
         */
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
         * 5. save PassiveSkill
         */
        PassiveSkill passiveSkill1 = PassiveSkill.builder()
                .support(false)
                .name("피쿼드호의 선장")
                .sinType(SinType.PRIDE)
                .passiveType(PassiveType.RESONANCE)
                .amount(3)
                .skillEffect(skillEffectPassive1)
                .build();

        PassiveSkill passiveSkill2 = PassiveSkill.builder()
                .support(false)
                .name("사냥 시간")
                .skillEffect(skillEffectPassive2)
                .build();

        PassiveSkill passiveSkill3 = PassiveSkill.builder()
                .support(true)
                .name("선장의 명령")
                .sinType(SinType.PRIDE)
                .passiveType(PassiveType.RESONANCE)
                .amount(3)
                .skillEffect(skillEffectPassive3)
                .build();

        passiveSkillRepository.save(passiveSkill1);
        passiveSkillRepository.save(passiveSkill2);
        passiveSkillRepository.save(passiveSkill3);


        /**
         * 6. save Identity
         */
        //Status & Resistances
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

        //Sanity
        Sanity sanity = Sanity.builder()
                .panic("한 턴 동안 행동하지 않음")
                .factorsIncreasingSanity("합 승리 시 합 횟수에 비례하여 증가\n(증가량: 기본 값 10, 2합부터 1합당 20%식 증가)\n적 처치 시 처치한 적의 레벨에 관계 없이 10 증가\n적 처치 시 처치한 적의 레벨이 자신의 레벨 이상인 경우 15 증가\n아군 사망 시 사망한 아군의 레벨에 관계 없이 10 증가")
                .factorsDecreasingSanity("아군이 처치한 적의 레벨이 자신의 레벨 이상인 경우 5 감소. 원호 공격이 부여된 아군이 처치한 경우에는 감소하지 않음.")
                .build();


        //Identity
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

    void save_R사_제_4무리_순록팀() {

        /**
         * 1. save OnHitEffect
         */
        OnHitEffect onHitEffect1Coin1 = OnHitEffect.builder()
                .coin(1)
                .effect("[적중시] 침잠 1 부여\n[적중시] 자신의 충전 횟수 2 증가")
                .build();
        OnHitEffect onHitEffect1Coin2 = OnHitEffect.builder()
                .coin(2)
                .effect("[적중시] 침잠 1 부여\n[적중시] 자신의 충전 횟수 2 증가")
                .build();
        OnHitEffect onHitEffect2Coin1 = OnHitEffect.builder()
                .coin(1)
                .effect("[적중시] 침잠 횟수 3 증가")
                .build();
        OnHitEffect onHitEffect3Coin1 = OnHitEffect.builder()
                .coin(1)
                .effect("[적중시] 침잠 1부여\n[적중시] 피해량의 40%만큼 흐트러짐 손상을 입힘")
                .build();
        OnHitEffect onHitEffect3Coin2 = OnHitEffect.builder()
                .coin(2)
                .effect("[적중시] 침잠 1부여\n[적중시] 피해량의 30%만큼 흐트러짐 손상을 입힘")
                .build();
        OnHitEffect onHitEffect3Coin3 = OnHitEffect.builder()
                .coin(3)
                .effect("[적중시] 침잠 1부여\n[적중시] 피해량의 20%만큼 흐트러짐 손상을 입힘")
                .build();
        OnHitEffect onHitEffect3Coin4 = OnHitEffect.builder()
                .coin(4)
                .effect("[적중시] 침잠 1부여\n[적중시] 피해량의 10%만큼 흐트러짐 손상을 입힘")
                .build();

        onHitEffectRepository.save(onHitEffect1Coin1);
        onHitEffectRepository.save(onHitEffect1Coin2);
        onHitEffectRepository.save(onHitEffect2Coin1);
        onHitEffectRepository.save(onHitEffect3Coin1);
        onHitEffectRepository.save(onHitEffect3Coin2);
        onHitEffectRepository.save(onHitEffect3Coin3);
        onHitEffectRepository.save(onHitEffect3Coin4);

        /**
         * 2. save SkillEffect
         */
        SkillEffect skillEffect1 = SkillEffect.builder()
                .effect("[사용시] 자신의 충전 횟수 2 증가")
                .onHitEffects(List.of(onHitEffect1Coin1, onHitEffect1Coin2))
                .build();
        SkillEffect skillEffect2 = SkillEffect.builder()
                .effect("[사용시] 자신의 충전 횟수 7 증가")
                .onHitEffects(List.of(onHitEffect2Coin1))
                .build();
        SkillEffect skillEffect3 = SkillEffect.builder()
                .effect("충전 횟수 8 소모.\n전투 시작 시 충전 횟수가 8 미만이면 무작위 대상 일방 공격")
                .onHitEffects(List.of(onHitEffect3Coin1, onHitEffect3Coin2, onHitEffect3Coin3, onHitEffect3Coin4))
                .build();
        SkillEffect skillEffectDefense = SkillEffect.builder()
                .effect("[회피 성공시] 자신의 충전 횟수 1 증가")
                .onHitEffects(new ArrayList<>())
                .build();
        SkillEffect skillEffectPassive1 = SkillEffect.builder()
                .effect("전투 시작 시 공격 위력 증가를 (우울 공명 수/3)만큼 얻음\n턴 종료시 이번 턴에 피해를 주지 못한 경우 정신력 -15")
                .onHitEffects(new ArrayList<>())
                .build();
        SkillEffect skillEffectPassive2 = SkillEffect.builder()
                .effect("정신력이 가장 낮은 아군 1명 타격 스킬의 피해량 + 10%")
                .onHitEffects(new ArrayList<>())
                .build();

        skillEffectRepository.save(skillEffect1);
        skillEffectRepository.save(skillEffect2);
        skillEffectRepository.save(skillEffect3);
        skillEffectRepository.save(skillEffectDefense);
        skillEffectRepository.save(skillEffectPassive1);
        skillEffectRepository.save(skillEffectPassive2);


        /**
         * 3. save OffenseSkill
         */
        OffenseSkill offenseSkill1 = OffenseSkill.builder()
                .slot(1)
                .name("정신 가격")
                .level(41)
                .offenseType(OffenseType.BLUNT)
                .sinType(SinType.GLOOM)
                .amount(3)
                .skillPower(4)
                .coinPower(5)
                .coinNumber(2)
                .weight(1)
                .skillEffect(skillEffect1)
                .build();

        OffenseSkill offenseSkill2 = OffenseSkill.builder()
                .slot(2)
                .name("지져내기")
                .level(41)
                .offenseType(OffenseType.BLUNT)
                .sinType(SinType.ENVY)
                .amount(2)
                .skillPower(6)
                .coinPower(12)
                .coinNumber(1)
                .weight(1)
                .skillEffect(skillEffect2)
                .build();

        OffenseSkill offenseSkill3 = OffenseSkill.builder()
                .slot(3)
                .name("정신 채찍")
                .level(41)
                .offenseType(OffenseType.BLUNT)
                .sinType(SinType.WRATH)
                .amount(1)
                .skillPower(2)
                .coinPower(6)
                .coinNumber(4)
                .weight(1)
                .skillEffect(skillEffect3)
                .build();

        offenseSkillRepository.save(offenseSkill1);
        offenseSkillRepository.save(offenseSkill2);
        offenseSkillRepository.save(offenseSkill3);


        /**
         * 4. save DefenseSkill
         */
        DefenseSkill defenseSkill = DefenseSkill.builder()
                .name("회피")
                .level(43)
                .defenseType(DefenseType.EVASION)
                .sinType(SinType.GLOOM)
                .skillPower(4)
                .coinPower(10)
                .skillEffect(skillEffectDefense)
                .build();

        defenseSkillRepository.save(defenseSkill);


        /**
         * 5. save PassiveSkill
         */
        PassiveSkill passiveSkill1 = PassiveSkill.builder()
                .support(false)
                .name("뇌파집속")
                .sinType(SinType.GLOOM)
                .passiveType(PassiveType.RESONANCE)
                .amount(3)
                .skillEffect(skillEffectPassive1)
                .build();

        PassiveSkill passiveSkill2 = PassiveSkill.builder()
                .support(true)
                .name("집념")
                .sinType(SinType.GLOOM)
                .passiveType(PassiveType.OWNED)
                .amount(5)
                .skillEffect(skillEffectPassive2)
                .build();

        passiveSkillRepository.save(passiveSkill1);
        passiveSkillRepository.save(passiveSkill2);


        /**
         * 6. save Identity
         */

        //스테이터스 & 내성정보
        Status status = Status.builder()
                .hp(187)
                .speed("3-7")
                .defenseLevel(43)
                .build();
        Resistances resistances = Resistances.builder()
                .slashResistance(2.0)
                .pierceResistance(1.0)
                .bluntResistance(0.5)
                .build();
        
        //정신력
        Sanity sanity = Sanity.builder()
                .panic("한 턴 동안 행동하지 않음")
                .factorsIncreasingSanity("합 승리 시 합 횟수에 비례하여 증가\n(증가량: 기본 값 10, 2합부터 1합당 20%식 증가)\n적 처치 시 처치한 적의 레벨이 자신의 레벨 이상일 경우 10 증가\n아군이 처치한 적의 레벨이 자신의 레벨 이상일 경우 5 증가")
                .factorsDecreasingSanity("아군 사망 시 사망한 아군의 레벨이 자신의 레벨 이상인 경우, 레벨 차에 따라 감소\n(감소량: 기본 값 10, 레벨 차이당 10씩 증가)")
                .build();



        /**
         * 인격 생성
         */
        Identity identity = Identity.builder()
                .sinner(Sinner.ISHMAEL)
                .name("R사 제 4무리 순록팀")
                .rarity(3)
                .status(status)
                .resistances(resistances)
                .offenseSkills(List.of(offenseSkill1, offenseSkill2, offenseSkill3))
                .defenseSkill(defenseSkill)
                .passiveSkills(List.of(passiveSkill1, passiveSkill2))
                .sanity(sanity)
                .build();

        identityRepository.save(identity);
    }

    @Test
    void findById() {
        //given
        Long id = identityRepository.findByName("피쿼드호 선장").get(0).getId();

        //when
        Identity identity = identityRepository.findById(id).get();

        //then
        assertNotNull(identity);
        assertEquals(2, identityRepository.count());
        assertEquals(37, identity.getStatus().getDefenseLevel());
        assertEquals(2, identity.getOffenseSkills().get(1).getSkillEffect().getOnHitEffects().size());
    }

    @Test
    void findBySinnerTest() {

        //given
        //save ISHMAEL2, Not YI_SANG

        //when
        List<Identity> identities_ISHMAEL = identityRepository.findBySinner(Sinner.ISHMAEL);
        List<Identity> identities_YI_SANG = identityRepository.findBySinner(Sinner.YI_SANG);


        //then
        assertEquals(2, identities_ISHMAEL.size());
        assertEquals(0, identities_YI_SANG.size());
    }

    @Test
    void findByNameTest() {
        //given

        //when
        Identity identity = identityRepository.findByName("피쿼드호 선장").get(0);
        Identity identity_선장 = identityRepository.findByName("선장").get(0);

        //then
        assertEquals(identity, identity_선장);
    }

    @Test
    void findByRarityTest() {

        //given

        //when
        List<Identity> identities = identityRepository.findByRarity(3);

        //then
        assertEquals(2, identities.size());
    }

}