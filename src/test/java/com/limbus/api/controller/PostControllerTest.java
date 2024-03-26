package com.limbus.api.controller;

import com.limbus.api.domain.Post;
import com.limbus.api.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;


    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청 시 Hello World 를 출력한다.")
    void test() throws Exception {
        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\" : \"제목입니다.\", \"content\" : \"내용입니다.\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{}"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/posts 요청 시 title 값은 필수다.")
    void test2() throws Exception {
        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\" : null, \"content\" : \"내용입니다.\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("/posts 요청 시 DB에 값이 저장된다.")
    void test3() throws Exception {
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\" : \"제목입니다.\",  \"content\" : \"내용입니다.\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // db -> post 1개 등록
        // then
        assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

}