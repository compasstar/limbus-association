package com.limbus.api.controller;

import com.limbus.api.response.IdentityResponse;
import com.limbus.api.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IdentityController {

    private final IdentityService identityService;

    @GetMapping("/identities/{englishName}")
    public IdentityResponse getIdentityByEnglishName(@PathVariable(name = "englishName") String name) {
        return identityService.getIdentityByEnglishName(name);
    }
}
