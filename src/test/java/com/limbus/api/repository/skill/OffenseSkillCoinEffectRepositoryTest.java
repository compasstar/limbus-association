package com.limbus.api.repository.skill;

import com.limbus.api.BeforeTest;
import com.limbus.api.domain.skill.OffenseSkill;
import com.limbus.api.domain.skill.OffenseSkillCoinEffect;
import com.limbus.api.repository.identity.IdentityRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OffenseSkillCoinEffectRepositoryTest {

    @Autowired
    OffenseSkillCoinEffectRepository offenseSkillCoinEffectRepository;

    @Autowired
    OffenseSkillRepository skillRepository;

    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    BeforeTest beforeTest = new BeforeTest();
    @Autowired
    private OffenseSkillRepository offenseSkillRepository;

    @BeforeEach
    void before() {
        beforeTest.saveIdentities();
    }

    @Test
    void findByOffenseSkillId() {

        //when
        Long identityId = identityRepository.findByName("피쿼드호 선장").get(0).getId();
        List<OffenseSkill> offenseSkills = offenseSkillRepository.findByIdentityId(identityId);

        List<OffenseSkillCoinEffect> offenseSkill1CoinEffects = offenseSkillCoinEffectRepository.findByOffenseSkillId(offenseSkills.get(0).getId());
        List<OffenseSkillCoinEffect> offenseSkill2CoinEffects = offenseSkillCoinEffectRepository.findByOffenseSkillId(offenseSkills.get(1).getId());
        List<OffenseSkillCoinEffect> offenseSkill3CoinEffects = offenseSkillCoinEffectRepository.findByOffenseSkillId(offenseSkills.get(2).getId());

        //then
        assertEquals(1, offenseSkill1CoinEffects.size());
        assertEquals(2, offenseSkill1CoinEffects.get(0).getCoin());
        assertEquals("[적중시] 출혈 2 부여", offenseSkill1CoinEffects.get(0).getEffect());

        assertEquals(2, offenseSkill2CoinEffects.size());
        assertEquals(2, offenseSkill2CoinEffects.get(0).getCoin());
        assertEquals("[적중시] 출혈 2 부여", offenseSkill2CoinEffects.get(0).getEffect());
        assertEquals(3, offenseSkill2CoinEffects.get(1).getCoin());
        assertEquals("[적중시] 화상 1 부여", offenseSkill2CoinEffects.get(1).getEffect());

        assertEquals(3, offenseSkill3CoinEffects.size());
        assertEquals(2, offenseSkill3CoinEffects.get(0).getCoin());
        assertEquals("[적중시] 출혈 횟수 1 증가", offenseSkill3CoinEffects.get(0).getEffect());
        assertEquals(3, offenseSkill3CoinEffects.get(1).getCoin());
        assertEquals("[적중시] 출혈 2 부여", offenseSkill3CoinEffects.get(1).getEffect());
        assertEquals(4, offenseSkill3CoinEffects.get(2).getCoin());
        assertEquals("[적중시] 화상 횟수 1 증가", offenseSkill3CoinEffects.get(2).getEffect());

    }
}