package com.limbus.api.controller;

import com.limbus.api.BeforeTest;
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
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        identityRepository.deleteAll();
        beforeTest.saveIdentities();
    }

    @Test
    @DisplayName("인격 단건 조회")
    void getIdentityTest() throws Exception {
        //expected
        this.mockMvc.perform(get("/identities/{englishName}", "The_Pequod_Captain_Ishmael").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("identity-inquiry",
                            pathParameters(
                                    parameterWithName("englishName").description("수감자 영문이름")
                            ),
                            responseFields(
                                    fieldWithPath("sinner").description("수감자"),
                                    fieldWithPath("name").description("이름"),
                                    fieldWithPath("englishName").description("영문이름"),
                                    fieldWithPath("rarity").description("등급"),

                                    fieldWithPath("status.hp").description("체력"),
                                    fieldWithPath("status.minSpeed").description("속도최솟값"),
                                    fieldWithPath("status.maxSpeed").description("속도최댓값"),
                                    fieldWithPath("status.defenseLevel").description("방어레벨"),

                                    fieldWithPath("resistances.slashResistance").description("참격내성"),
                                    fieldWithPath("resistances.pierceResistance").description("관통내성"),
                                    fieldWithPath("resistances.bluntResistance").description("타격내성"),

                                    fieldWithPath("sanity.panic[]").description("패닉"),
                                    fieldWithPath("sanity.factorsIncreasingSanity[]").description("정신력증가조건"),
                                    fieldWithPath("sanity.factorsDecreasingSanity[]").description("정신력감소조건"),

                                    fieldWithPath("offenseSkills[].slot").description("스킬 슬롯"),
                                    fieldWithPath("offenseSkills[].name").description("스킬 이름"),
                                    fieldWithPath("offenseSkills[].level").description("스킬 공격레벨"),
                                    fieldWithPath("offenseSkills[].offenseType").description("스킬 공격타입"),
                                    fieldWithPath("offenseSkills[].sinType").description("스킬 죄악타입"),
                                    fieldWithPath("offenseSkills[].amount").description("스킬 스킬개수"),
                                    fieldWithPath("offenseSkills[].skillPower").description("스킬 기본위력"),
                                    fieldWithPath("offenseSkills[].coinPower").description("스킬 코인위력"),
                                    fieldWithPath("offenseSkills[].coinNumber").description("스킬 코인개수"),
                                    fieldWithPath("offenseSkills[].weight").description("스킬 가중치"),
                                    fieldWithPath("offenseSkills[].effect[]").description("스킬 효과"),

                                    fieldWithPath("offenseSkills[].offenseSkillCoinEffects[].coin").description("스킬 적중시 코인"),
                                    fieldWithPath("offenseSkills[].offenseSkillCoinEffects[].effect[]").description("스킬 적중시 효과"),

                                    fieldWithPath("defenseSkills[].name").description("수비 이름"),
                                    fieldWithPath("defenseSkills[].level").description("수비 수비레벨"),
                                    fieldWithPath("defenseSkills[].defenseType").description("수비 타입"),
                                    fieldWithPath("defenseSkills[].sinType").description("수비 죄악타입").optional(),
                                    fieldWithPath("defenseSkills[].skillPower").description("수비 기본위력").optional(),
                                    fieldWithPath("defenseSkills[].coinPower").description("수비 코인위력").optional(),
                                    fieldWithPath("defenseSkills[].coinNumber").description("수비 코인개수").optional(),
                                    fieldWithPath("defenseSkills[].effect[]").description("수비 효과").optional(),
                                    fieldWithPath("defenseSkills[].weight").description("수비 가중치").optional(),

                                    fieldWithPath("passiveSkills[].support").description("패시브 서포트스킬"),
                                    fieldWithPath("passiveSkills[].name").description("패시브 이름"),
                                    fieldWithPath("passiveSkills[].sinType").description("패시브 죄악속성").optional(),
                                    fieldWithPath("passiveSkills[].passiveType").description("패시브 타입(보유, 공명)").optional(),
                                    fieldWithPath("passiveSkills[].amount").description("패시브 개수(몇 개 보유, 몇 개 공명").optional(),
                                    fieldWithPath("passiveSkills[].effect[]").description("패시브 효과")
                            )
                        )
                );
    }
}
