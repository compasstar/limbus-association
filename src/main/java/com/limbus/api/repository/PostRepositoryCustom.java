package com.limbus.api.repository;

import com.limbus.api.domain.Post;
import com.limbus.api.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
