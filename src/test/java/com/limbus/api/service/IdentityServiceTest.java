package com.limbus.api.service;

import com.limbus.api.BeforeTest;
import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.identity.Resistances;
import com.limbus.api.domain.identity.Sanity;
import com.limbus.api.domain.identity.Status;
import com.limbus.api.domain.skill.*;
import com.limbus.api.domain.type.*;
import com.limbus.api.repository.identity.IdentityRepository;
import com.limbus.api.repository.skill.*;
import com.limbus.api.response.IdentityResponse;
import com.limbus.api.response.skill.OffenseSkillResponse;
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
class IdentityServiceTest {

    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    IdentityService identityService;

    @Autowired
    BeforeTest beforeTest;

    @BeforeEach
    void saveIdentities() {
        beforeTest.saveIdentities();
    }

    @Test
    @DisplayName("인격 1개 조회")
    void getIdentityTest() {
        //when
        Identity identity = identityRepository.findByName("피쿼드호 선장").get(0);
        IdentityResponse identityResponse = identityService.getIdentity(identity.getId());

        //then
        assertNotNull(identityResponse);
        assertEquals(160, identityResponse.getStatus().getHp());
        assertEquals("끝까지 추적한다!", identityResponse.getOffenseSkills().get(1).getName());

        List<OffenseSkillResponse> offenseSkillResponses = identityResponse.getOffenseSkills();
        assertEquals("[적중시] 화상 1 부여", offenseSkillResponses.get(1).getOffenseSkillCoinEffects().get(1).getEffect());

        assertEquals("공포를 날려주지", identityResponse.getDefenseSkills().get(0).getName());
        assertEquals("사냥 시간", identityResponse.getPassiveSkills().get(1).getName());
    }

    @Test
    @DisplayName("없는 인격 조회 -> throws IllegalArgumentException")
    void getNullIdentityTest() {
        assertThrows(IllegalArgumentException.class, () -> identityService.getIdentity(9999L));
    }

}