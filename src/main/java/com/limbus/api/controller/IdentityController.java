package com.limbus.api.controller;

import com.limbus.api.response.IdentityResponse;
import com.limbus.api.response.IdentitySearchResponse;
import com.limbus.api.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class IdentityController {

    private final IdentityService identityService;

    @GetMapping("/identities/{englishName}")
    public IdentityResponse getIdentityByEnglishName(@PathVariable(name = "englishName") String name) {
        return identityService.getIdentityByEnglishName(name);
    }

    @GetMapping("/identities/search")
    public IdentitySearchResponse searchIdentity(@RequestParam(name = "name") String name) {
        return identityService.searchIdentity(name);
    }
}
