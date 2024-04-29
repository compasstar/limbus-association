package com.limbus.api.repository.identity;

import com.limbus.api.BeforeTest;
import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.Sinner;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class IdentityRepositoryTest {

    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    BeforeTest beforeTest = new BeforeTest();

    @BeforeEach
    void before() {
        beforeTest.saveIdentities();
    }

    @Test
    void findById() {
        //when
        Long id = identityRepository.findByName("피쿼드호 선장").get(0).getId();
        Identity identity = identityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 인격이 존재하지 않습니다"));

        //then
        assertNotNull(identity);
        assertEquals(id, identity.getId());
        assertEquals(Sinner.ISHMAEL, identity.getSinner());
        assertEquals("피쿼드호 선장", identity.getName());
        assertEquals(3, identity.getRarity());

        assertEquals(160, identity.getStatus().getHp());
        assertEquals(4, identity.getStatus().getMinSpeed());
        assertEquals(7, identity.getStatus().getMaxSpeed());
        assertEquals(37, identity.getStatus().getDefenseLevel());

        assertEquals(1.0, identity.getResistances().getSlashResistance());
        assertEquals(0.5, identity.getResistances().getPierceResistance());
        assertEquals(2.0, identity.getResistances().getBluntResistance());

        assertEquals("한 턴 동안 행동하지 않음", identity.getSanity().getPanic());
        assertEquals("합 승리 시 합 횟수에 비례하여 증가\n(증가량: 기본 값 10, 2합부터 1합당 20%식 증가)\n적 처치 시 처치한 적의 레벨에 관계 없이 10 증가\n적 처치 시 처치한 적의 레벨이 자신의 레벨 이상인 경우 15 증가\n아군 사망 시 사망한 아군의 레벨에 관계 없이 10 증가", identity.getSanity().getFactorsIncreasingSanity());
        assertEquals("아군이 처치한 적의 레벨이 자신의 레벨 이상인 경우 5 감소. 원호 공격이 부여된 아군이 처치한 경우에는 감소하지 않음.", identity.getSanity().getFactorsDecreasingSanity());

        assertEquals(3, identity.getOffenseSkills().size());
        assertEquals(1, identity.getDefenseSkills().size());
        assertEquals(3, identity.getPassiveSkills().size());
    }

    @Test
    void findBySinnerTest() {
        //when
        List<Identity> identities_ISHMAEL = identityRepository.findBySinner(Sinner.ISHMAEL);
        List<Identity> identities_YI_SANG = identityRepository.findBySinner(Sinner.YI_SANG);

        //then
        assertEquals(2, identities_ISHMAEL.size());
        assertEquals(0, identities_YI_SANG.size());
    }

    @Test
    void findByNameTest() {
        //when
        Identity identity = identityRepository.findByName("피쿼드호 선장").get(0);
        Identity identity_선장 = identityRepository.findByName("선장").get(0);

        //then
        assertEquals(identity, identity_선장);
    }

    @Test
    void findByRarityTest() {
        //when
        List<Identity> identities = identityRepository.findByRarity(3);

        //then
        assertEquals(2, identities.size());
    }
}