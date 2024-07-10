package com.limbus.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.limbus.api.domain.Session;
import com.limbus.api.domain.User;
import com.limbus.api.repository.SessionRepository;
import com.limbus.api.repository.UserRepository;
import com.limbus.api.request.Login;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 성공")
    void test() throws Exception {
        //given
        userRepository.save(User.builder()
                .name("호돌맨")
                .email("hodolman88@gmail.com")
                .password("1234")
                .build());

        //Scrypt, Bcrypt 비밀번호 암호화

        Login login = Login.builder()
                .email("hodolman88@gmail.com")
                .password("1234")
                .build();

        String json = objectMapper.writeValueAsString(login);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 성공 후 세션 1개 생성")
    @Transactional
    void test2() throws Exception {
        //given
        User user = userRepository.save(User.builder()
                .name("호돌맨")
                .email("hodolman88@gmail.com")
                .password("1234")
                .build());

        //Scrypt, Bcrypt 비밀번호 암호화

        Login login = Login.builder()
                .email("hodolman88@gmail.com")
                .password("1234")
                .build();

        String json = objectMapper.writeValueAsString(login);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals(1L, user.getSessions().size());
    }

    @Test
    @DisplayName("로그인 성공 후 세션 응답")
    void test3() throws Exception {
        //given
        User user = userRepository.save(User.builder()
                .name("호돌맨")
                .email("hodolman88@gmail.com")
                .password("1234")
                .build());

        //Scrypt, Bcrypt 비밀번호 암호화

        Login login = Login.builder()
                .email("hodolman88@gmail.com")
                .password("1234")
                .build();

        String json = objectMapper.writeValueAsString(login);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken", Matchers.notNullValue()))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 후 권한이 필요한 페이지 접속한다 /foo")
    void test4() throws Exception {
        //given
        User user = User.builder()
                .name("호돌맨")
                .email("hodolman88@gmail.com")
                .password("1234")
                .build();
        Session session = user.addSession();
        userRepository.save(user);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/foo")
                        .header("Authorization", session.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 후 검증되지 않은 세션값으로 권한이 필요한 페이지에 접속할 수 없다")
    void test5() throws Exception {
        //given
        User user = User.builder()
                .name("호돌맨")
                .email("hodolman88@gmail.com")
                .password("1234")
                .build();
        Session session = user.addSession();
        userRepository.save(user);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/foo")
                        .header("Authorization", session.getAccessToken() + "-other")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

}