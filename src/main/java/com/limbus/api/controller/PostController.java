package com.limbus.api.controller;

import com.limbus.api.request.PostCreate;
import com.limbus.api.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@Valid @RequestBody PostCreate request) {
        //Case1. 저장한 데이터 Entity -> response 로 응답하기
        //Case2. 저장한 데이터의 primary_id -> response 로 응답하기
        //      Client 에서는 수신한 id를 글 조회 API 를 통해서 글 데이터를 수신받음
        //Case3. 응답 필요 없음 -> 클라이언트에서 모든 POST(글) 데이터 context 를 잘 관리함.
        //Bad Case: 서버에서 -> 반드시 이렇게 할껍니다! fix
        //          -> 서버에서 차라리 유연하게 대응하는게 좋습니다. -> 코드를 잘 짜야겠죠?
        //          -> 한 번에 일괄적으로 잘 처리되는 케이스가 없다 -> 잘 관리하는 형태가 중요
        postService.write(request);
    }

}
