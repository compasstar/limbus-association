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

    public IdentityResponse getIdentityById(Long id) {
        Identity identity = identityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no Identity of That ID"));
        return new IdentityResponse(identity);
    }

    public IdentityResponse getIdentityByEnglishName(String englishName) {
        Identity identity = identityRepository.findByEnglishName(englishName)
                .orElseThrow(() -> new IllegalArgumentException("There is no Identity of That English Name"));
        return new IdentityResponse(identity);
    }

}
