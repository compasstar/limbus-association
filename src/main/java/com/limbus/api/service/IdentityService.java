package com.limbus.api.service;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.Sinner;
import com.limbus.api.repository.identity.IdentityRepository;
import com.limbus.api.response.IdentityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IdentityService {

    private final IdentityRepository identityRepository;

    public IdentityResponse getIdentityByEnglishName(String englishName) {
        Identity identity = identityRepository.findByEnglishName(englishName)
                .orElseThrow(() -> new IllegalArgumentException("There is no Identity of That English Name"));
        return new IdentityResponse(identity);
    }

    public List<IdentityResponse> searchIdentity(String name) {
        List<IdentityResponse> identityResponses = new ArrayList<>();
        List<Identity> identities = identityRepository.findByPartName(name);
        identities.forEach(identity -> {
            identityResponses.add(new IdentityResponse(identity));
        });
        return identityResponses;
    }

    public List<IdentityResponse> searchIdentityBySinner(String sinner) {
        List<IdentityResponse> identityResponses = new ArrayList<>();
        List<Identity> identities = identityRepository.findBySinner(Sinner.valueOf(sinner));
        identities.forEach(identity -> {
            identityResponses.add(new IdentityResponse(identity));
        });
        return identityResponses;
    }


    public List<IdentityResponse> getAllIdentities() {
        List<IdentityResponse> identityResponses = new ArrayList<>();
        List<Identity> identities = identityRepository.findAll();
        identities.forEach(identity -> {
            identityResponses.add(new IdentityResponse(identity));
        });
        return identityResponses;
    }
}
