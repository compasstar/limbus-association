package com.limbus.api.controller;

import com.limbus.api.exception.InvalidRequest;
import com.limbus.api.request.PostCreate;
import com.limbus.api.request.PostEdit;
import com.limbus.api.request.PostSearch;
import com.limbus.api.response.PostResponse;
import com.limbus.api.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@Valid @RequestBody PostCreate request) {
        request.validate();
        postService.write(request);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name = "postId") Long postId) {
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable("postId") Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable("postId") Long postId) {
        postService.delete(postId);
    }

}
