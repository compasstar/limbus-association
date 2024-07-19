package com.limbus.api.config;

import com.limbus.api.config.data.UserSession;
import com.limbus.api.domain.Session;
import com.limbus.api.exception.Unauthorized;
import com.limbus.api.repository.SessionRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.crypto.SecretKey;
import java.util.Base64;

/**
 * 파라미터로 특정 클래스(DTO)가 들어왔을 때, 해당 클래스의 변수값들을 미리 여기서 채워줄 수 있다.
 * supportsParameter: 만약 파라미터로 UserSession.class 가 들어온다면
 * resolveArgument: UserSession 클래스의 name 변수값을 accessToken 으로 채운다
 *
 * 인증이 필요한 메서드는 반드시 해당 DTO 를 파라미터로 받아야 한다... 라는 방식으로도 사용 가능
 */
@Slf4j
@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {

    private static final String KEY = "cdTqQ6AXjQj1Ml306xIhexxcHW4tCWOUn923Zx2pUUo=";
    private final SessionRepository sessionRepository;


    /**
     * 받은 파라미터의 타입이 UserSession.class 라면 true 를 반환한다
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String jws = webRequest.getHeader("Authorization");
        if (jws == null || jws.isEmpty()) {
            throw new Unauthorized();
        }

        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));
        try {
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(jws);

            //사실 아래 코드는 필요 없음.
            // 결국 클라이언트로 return 하는 값은 Controller 의 return 값이다. 아래의 return 과는 무관함.
            String userId = claims.getBody().getSubject();
            return new UserSession(Long.parseLong(userId));
        } catch (JwtException e) {
            throw new Unauthorized();
        }

//        return new UserSession(session.getUser().getId());
    }
}
