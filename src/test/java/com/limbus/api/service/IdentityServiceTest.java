package com.limbus.api.service;

import com.limbus.api.BeforeTest;
import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.Sinner;
import com.limbus.api.repository.identity.IdentityRepository;
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
        identityRepository.deleteAll();
        beforeTest.saveIdentities();
    }

    @Test
    @DisplayName("인격 1개 조회")
    void getIdentityByEnglishName() {
        //when
        IdentityResponse identityResponse = identityService.getIdentityByEnglishName("The_Pequod_Captain_Ishmael");

        //then
        assertNotNull(identityResponse);
        assertEquals(160, identityResponse.getStatus().getHp());
        assertEquals("끝까지 추적한다!", identityResponse.getOffenseSkills().get(1).getName());

        List<OffenseSkillResponse> offenseSkillResponses = identityResponse.getOffenseSkills();
        assertEquals("[적중시] 화상 1 부여", offenseSkillResponses.get(1).getOffenseSkillCoinEffects().get(1).getEffect().get(0));

        assertEquals("공포를 날려주지", identityResponse.getDefenseSkills().get(0).getName());
        assertEquals("사냥 시간", identityResponse.getPassiveSkills().get(1).getName());
    }

    @Test
    @DisplayName("없는 인격 조회 -> throws IllegalArgumentException")
    void getNullIdentityTest() {
        assertThrows(IllegalArgumentException.class, () -> identityService.getIdentityByEnglishName("의문의 인격"));
    }

    @Test
    @DisplayName("인격 이름으로 검색")
    void searchIdentity() {
        //when
        List<IdentityResponse> identities = identityService.searchIdentity("피쿼드호");

        //then
        assertEquals(1, identities.size());
        assertEquals("피쿼드호 선장", identities.get(0).getName());
    }

    @Test
    @DisplayName("수감자 별 검색")
    void searchIdentityBySinner() {
        //when
        List<IdentityResponse> identities = identityService.searchIdentityBySinner("ISHMAEL");

        //then
        assertEquals(2, identities.size());
    }

    @Test
    @DisplayName("모든 수감자 불러오기")
    void getAllIdentities() {
        //when
        List<IdentityResponse> allIdentities = identityService.getAllIdentities();

        //then
        assertEquals(2, allIdentities.size());
    }

}