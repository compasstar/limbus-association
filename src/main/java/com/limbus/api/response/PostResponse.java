package com.limbus.api.response;

import com.limbus.api.domain.Post;
import lombok.Builder;
import lombok.Getter;


@Getter
public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;

    @Builder
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle().substring(0, Math.min(post.getTitle().length(), 10));
        this.content = post.getContent();
    }


}
