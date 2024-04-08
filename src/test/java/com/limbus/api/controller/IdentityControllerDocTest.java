package com.limbus.api.controller;

import com.limbus.api.BeforeTest;
import com.limbus.api.domain.identity.Identity;
import com.limbus.api.repository.identity.IdentityRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "limbus.association.com", uriPort = 443)
@Transactional
@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
public class IdentityControllerDocTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IdentityRepository identityRepository;

    @Autowired
    BeforeTest beforeTest;

    @BeforeEach
    void saveIdentities() {
        beforeTest.saveIdentities();
    }

    @Test
    @DisplayName("인격 단건 조회")
    void getIdentityTest() throws Exception {
        //expected
        this.mockMvc.perform(get("/identity/{identityId}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("identity-inquiry",
                            pathParameters(
                                    parameterWithName("identityId").description("ID")
                            ),
                            responseFields(
                                    fieldWithPath("name").description("이름"),
                                    fieldWithPath("rarity").description("등급"),

                                    fieldWithPath("status.hp").description("스테이터스 > 체력"),
                                    fieldWithPath("status.speed").description("스테이터스 > 속도"),
                                    fieldWithPath("status.defenseLevel").description("스테이터스 > 방어레벨"),

                                    fieldWithPath("resistances.slashResistance").description("내성 > 참격내성"),
                                    fieldWithPath("resistances.pierceResistance").description("내성 > 관통내성"),
                                    fieldWithPath("resistances.bluntResistance").description("내성 > 타격내성"),

                                    fieldWithPath("offenseSkills[0].slot").description("스킬1 > 슬롯"),
                                    fieldWithPath("offenseSkills[0].name").description("스킬1 > 이름"),
                                    fieldWithPath("offenseSkills[0].level").description("스킬1 > 공격레벨"),
                                    fieldWithPath("offenseSkills[0].offenseType").description("스킬1 > 공격타입"),
                                    fieldWithPath("offenseSkills[0].sinType").description("스킬1 > 죄악타입"),
                                    fieldWithPath("offenseSkills[0].amount").description("스킬1 > 스킬개수"),
                                    fieldWithPath("offenseSkills[0].skillPower").description("스킬1 > 기본위력"),
                                    fieldWithPath("offenseSkills[0].coinPower").description("스킬1 > 코인위력"),
                                    fieldWithPath("offenseSkills[0].coinNumber").description("스킬1 > 코인개수"),
                                    fieldWithPath("offenseSkills[0].weight").description("스킬1 > 가중치"),

                                    fieldWithPath("offenseSkills[0].skillEffect.effect").description("스킬1 > 스킬효과 > 효과"),
                                    fieldWithPath("offenseSkills[0].skillEffect.onHitEffects[0].coin").description("스킬1 > 스킬효과 > 적중시효과 > 코인"),
                                    fieldWithPath("offenseSkills[0].skillEffect.onHitEffects[0].effect").description("스킬1 > 스킬효과 > 적중시효과 > 효과"),

                                    fieldWithPath("defenseSkill.name").description("수비 > 이름"),
                                    fieldWithPath("defenseSkill.level").description("수비 > 수비레벨"),
                                    fieldWithPath("defenseSkill.defenseType").description("수비 > 타입"),
                                    fieldWithPath("defenseSkill.sinType").description("수비 > 죄악타입"),
                                    fieldWithPath("defenseSkill.skillPower").description("수비 > 기본위력"),
                                    fieldWithPath("defenseSkill.coinPower").description("수비 > 코인위력"),
                                    fieldWithPath("defenseSkill.skillEffect.effect").description("수비 > 스킬효과 > 효과"),
                                    fieldWithPath("defenseSkill.skillEffect.onHitEffects").description("수비 > 스킬효과 > 적중시효과"),

                                    fieldWithPath("passiveSkills[0].name").description("패시브 > 이름"),
                                    fieldWithPath("passiveSkills[0].sinType").description("패시브 > 죄악속성").optional(),
                                    fieldWithPath("passiveSkills[0].passiveType").description("패시브 > 타입(보유, 공명)").optional(),
                                    fieldWithPath("passiveSkills[0].amount").description("패시브 > 개수(몇 개 보유, 몇 개 공명").optional(),
                                    fieldWithPath("passiveSkills[0].skillEffect.effect").description("패시브 > 스킬효과 > 효과"),
                                    fieldWithPath("passiveSkills[0].skillEffect.onHitEffects").description("패시브 > 스킬효과 > 적중시효과"),

                                    fieldWithPath("sanity.panic").description("정신력 > 패닉"),
                                    fieldWithPath("sanity.factorsIncreasingSanity").description("정신력 > 정신력증가조건"),
                                    fieldWithPath("sanity.factorsDecreasingSanity").description("정신력 > 정신력감소조건")
                            )
                        )
                );
    }
}
