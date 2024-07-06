package com.limbus.api.config;

import com.limbus.api.config.data.UserSession;
import com.limbus.api.exception.Unauthorized;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 파라미터로 특정 클래스(DTO)가 들어왔을 때, 해당 클래스의 변수값들을 미리 여기서 채워줄 수 있다.
 * supportsParameter: 만약 파라미터로 UserSession.class 가 들어온다면
 * resolveArgument: UserSession 클래스의 name 변수값을 accessToken 으로 채운다
 *
 * 인증이 필요한 메서드는 반드시 해당 DTO 를 파라미터로 받아야 한다... 라는 방식으로도 사용 가능
 */
public class AuthResolver implements HandlerMethodArgumentResolver {

    /**
     * 받은 파라미터의 타입이 UserSession.class 라면 true 를 반환한다
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String accessToken = webRequest.getHeader("Authorization");
        if (accessToken == null || accessToken.equals("")) {
            throw new Unauthorized();
        }

        // 데이터베이스 사용자 확인작업
        // ...

        return new UserSession(1L);
    }
}
