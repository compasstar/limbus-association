package com.limbus.api.repository;

import com.limbus.api.BeforeTest;
import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.identity.Resistances;
import com.limbus.api.domain.identity.Sanity;
import com.limbus.api.domain.identity.Status;
import com.limbus.api.domain.skill.*;
import com.limbus.api.domain.type.*;
import com.limbus.api.repository.identity.IdentityRepository;
import com.limbus.api.repository.skill.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        //given
        Long id = identityRepository.findByName("피쿼드호 선장").get(0).getId();

        //when
        Identity identity = identityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 인격이 존재하지 않습니다"));

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