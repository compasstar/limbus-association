package com.limbus.api.service;

import com.limbus.api.domain.Session;
import com.limbus.api.domain.User;
import com.limbus.api.exception.InvalidSigninInformation;
import com.limbus.api.repository.UserRepository;
import com.limbus.api.request.Login;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public String signin(Login login) {
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigninInformation::new);
        Session session = user.addSession();
        return session.getAccessToken();
    }
}
