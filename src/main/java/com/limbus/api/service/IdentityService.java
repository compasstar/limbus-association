package com.limbus.api.service;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.repository.identity.IdentityRepository;
import com.limbus.api.response.IdentityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdentityService {

    private final IdentityRepository identityRepository;

    public IdentityResponse getIdentity(Long id) {
        Identity identity = identityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 인격이 존재하지 않습니다."));

        return new IdentityResponse(identity);
    }

}
