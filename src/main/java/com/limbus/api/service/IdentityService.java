package com.limbus.api.service;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.repository.identity.IdentityRepository;
import com.limbus.api.response.IdentityResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdentityService {

    private final IdentityRepository identityRepository;

    public IdentityResponse getIdentity(Long id) {
        System.out.println("id = " + id);
        Identity identity = identityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no Identity of That ID"));

        return new IdentityResponse(identity);
    }

}
