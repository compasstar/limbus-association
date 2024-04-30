package com.limbus.api.controller;

import com.limbus.api.BeforeTest;
import com.limbus.api.domain.identity.Identity;
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

@AutoConfigureMockMvc
@Transactional //테스트가 끝나면 자동으로 롤백시킨다
@Rollback(value = false) // DB 에서 확인하고 싶으면 붙여주기
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
        beforeTest.saveIdentities();
    }

    @Test
    @DisplayName("/identities/{identityId} id에 해당하는 인격 1개 조회")
    void getIdentityTest() throws Exception {
        //given
        //@BeforeEach saveIdentities
        Identity identity = identityRepository.findByName("피쿼드호 선장").get(0);

        //expected
        mockMvc.perform(get("/identities/{identityId}", identity.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("피쿼드호 선장"))
                .andExpect(jsonPath("$.status.hp").value(identity.getStatus().getHp()))
                .andDo(print());
    }
}