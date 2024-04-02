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
        //given
        //BeforeEach -> save_피쿼드호_선장, R사 제 4무리 순록팀

        //when
        Identity identity = identityRepository.findByName("피쿼드호 선장").get(0);
        IdentityResponse identityResponse = identityService.getIdentity(identity.getId());

        //then
        assertNotNull(identityResponse);
        assertEquals(160, identityResponse.getStatus().getHp());
        assertEquals(3, identityResponse.getOffenseSkills().get(0).getAmount());
        assertEquals("대상이 <호수의 존재> 면 피해량 +10%", identityResponse.getPassiveSkills().get(1).getSkillEffect().getEffect());
    }

    @Test
    @DisplayName("없는 인격 조회 -> throws IllegalArgumentException")
    void getNullIdentityTest() {
        assertThrows(IllegalArgumentException.class, () -> identityService.getIdentity(9999L));
    }

}