package com.limbus.api.controller;

import com.limbus.api.request.Login;
import com.limbus.api.response.SessionResponse;
import com.limbus.api.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Base64;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private static final String KEY = "cdTqQ6AXjQj1Ml306xIhexxcHW4tCWOUn923Zx2pUUo=";
    private final AuthService authService;

    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login) {
        Long userId = authService.signin(login);

        //아래 코드로 KEY 값을 String 형태로 알아낼 수 있다
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        byte[] encodedKey = key.getEncoded();
//        String strKey = Base64.getEncoder().encodeToString(encodedKey);

        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));
        String jws = Jwts.builder().subject(String.valueOf(userId)).signWith(key).compact();
        return new SessionResponse(jws);
    }

}
