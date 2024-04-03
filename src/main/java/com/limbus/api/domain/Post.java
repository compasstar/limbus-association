package com.limbus.api.domain;

import com.limbus.api.request.PostCreate;
import com.limbus.api.request.PostEdit;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @Builder(builderMethodName = "dtoBuilder", buildMethodName = "dtoBuild")
    public Post(PostCreate postCreate) {
        this.title = postCreate.getTitle();
        this.content = postCreate.getContent();
    }

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void edit(PostEdit postEdit) {
        if (postEdit.getTitle() != null) {
            title = postEdit.getTitle();
        }
        if (postEdit.getContent() != null) {
            content = postEdit.getContent();
        }
    }



}
