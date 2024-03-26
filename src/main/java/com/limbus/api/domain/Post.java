package com.limbus.api.domain;

import com.limbus.api.request.PostCreate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    public Post(PostCreate postCreate) {
        this.title = postCreate.getTitle();
        this.content = postCreate.getContent();
    }
}
