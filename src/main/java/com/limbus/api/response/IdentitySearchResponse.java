package com.limbus.api.response;

import com.limbus.api.domain.identity.Identity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class IdentitySearchResponse {

    List<String> englishNames = new ArrayList<>();

    public IdentitySearchResponse(List<Identity> identities) {
        identities.forEach(identity -> englishNames.add(identity.getEnglishName()));
    }
}
