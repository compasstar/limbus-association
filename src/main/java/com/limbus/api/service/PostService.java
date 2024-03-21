package com.limbus.api.service;

import com.limbus.api.repository.PostRepository;
import com.limbus.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {

    }
}
