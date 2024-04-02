package com.limbus.api.service;

import com.limbus.api.domain.Post;
import com.limbus.api.repository.PostRepository;
import com.limbus.api.request.PostCreate;
import com.limbus.api.request.PostSearch;
import com.limbus.api.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글작성")
    void test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        postService.write(postCreate);

        //then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        //given
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost);

        //when
        PostResponse response = postService.get(requestPost.getId());

        //then
        assertNotNull(response);
        assertEquals(1L, postRepository.count());
        assertEquals("foo", response.getTitle());
        assertEquals("bar", response.getContent());

    }

    @Test
    @DisplayName("글 1페이지 조회")
    void test3() {
        //given
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Post.builder()
                            .title("제목 " + i)
                            .content("반포자이 " + i)
                            .build())
                .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);

//        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .size(5)
                .build();

        //when
        List<PostResponse> posts = postService.getList(postSearch);

        //then
        assertEquals(5L, posts.size());
        assertEquals("제목 30", posts.get(0).getTitle());
    }

}