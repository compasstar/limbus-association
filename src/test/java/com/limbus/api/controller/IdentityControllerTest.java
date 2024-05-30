package com.limbus.api.controller;

import com.limbus.api.BeforeTest;
import com.limbus.api.repository.identity.IdentityRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Rollback(value = false) // DB 에서 확인하고 싶으면 붙여주기
@AutoConfigureMockMvc
@Transactional //테스트가 끝나면 자동으로 롤백시킨다
@SpringBootTest
class IdentityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    BeforeTest beforeTest;

    @BeforeEach
    void saveIdentities() {
        identityRepository.deleteAll();
        beforeTest.saveIdentities();
    }

    @Test
    @DisplayName("/identities/{englishName} 이름에 해당하는 인격 1개 조회")
    void getIdentityByEnglishName() throws Exception {
        //expected
        mockMvc.perform(get("/identities/{englishName}", "The_Pequod_Captain_Ishmael"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("피쿼드호 선장"))
                .andExpect(jsonPath("$.status.hp").value(160))
                .andExpect(jsonPath("$.offenseSkills[0].name").value("내 주위에 서라!"))
                .andExpect(jsonPath("$.offenseSkills[0].offenseSkillCoinEffects[0].effect").value("[적중시] 출혈 2 부여"))
                .andExpect(jsonPath("$.defenseSkills[0].name").value("공포를 날려주지"))
                .andExpect(jsonPath("$.passiveSkills[0].name").value("피쿼드호의 선장"))
                .andDo(print());
    }

    @Test
    @DisplayName("수감자 이름으로 검색")
    void searchIdentity() throws Exception {
        //expected
        mockMvc.perform(get("/identities/search").param("name", "피쿼드호"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("피쿼드호 선장"))
                .andExpect(jsonPath("$.data[0].status.hp").value(160))
                .andDo(print());
    }

    @Test
    @DisplayName("수감자 별 검색")
    void searchIdentityBySinner() throws Exception {
        //expected
        mockMvc.perform(get("/identities/search/sinner").param("sinner", "ISHMAEL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("피쿼드호 선장"))
                .andExpect(jsonPath("$.data[0].status.hp").value(160))
                .andDo(print());
    }

    @Test
    @DisplayName("모든 수감자 조회")
    void getAllIdentities() throws Exception {
        //expected
        mockMvc.perform(get("/identities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()").value(2))
                .andDo(print());
    }
}