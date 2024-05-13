package com.limbus.api.response;

import com.limbus.api.domain.identity.Identity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class IdentitySearchResponse {

    List<Name> names = new ArrayList<>();

    public IdentitySearchResponse(List<Identity> identities) {
        identities.forEach(identity -> names.add(new Name(identity.getName(), identity.getEnglishName())));
    }

    public static class Name {
        public String name;
        public String englishName;

        public Name(String name, String englishName) {
            this.name = name;
            this.englishName = englishName;
        }
    }
}
