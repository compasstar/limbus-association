package com.limbus.api.controller;

import com.limbus.api.BeforeTest;
import com.limbus.api.domain.identity.Identity;
import com.limbus.api.repository.identity.IdentityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
public class IdentityControllerDocTest {

    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private IdentityRepository identityRepository;

    @Autowired
    BeforeTest beforeTest;

    @BeforeEach
    void saveIdentities() {
        beforeTest.saveIdentities();
    }

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("인격 1개 조회")
    void getIdentityTest() throws Exception {
        //given
        Identity identity = identityRepository.findByName("피쿼드호 선장").get(0);

        //expected
        this.mockMvc.perform(get("/identity/{identityId}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("피쿼드호 선장"))
                .andExpect(jsonPath("$.status.hp").value(identity.getStatus().getHp()))
                .andExpect(jsonPath("$.defenseSkill.name").value("공포를 날려주지"))
                .andExpect(jsonPath("$.offenseSkills[0].skillEffect.onHitEffects[0].coin").value(identity.getOffenseSkills().get(0).getSkillEffect().getOnHitEffects().get(0).getCoin()))
                .andDo(document("index"));
    }
}
