package com.limbus.api.controller;

import com.limbus.api.response.IdentityResponse;
import com.limbus.api.response.IdentitySearchResponse;
import com.limbus.api.response.Result;
import com.limbus.api.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IdentityController {

    private final IdentityService identityService;

    @GetMapping("/identities/{englishName}")
    public IdentityResponse getIdentityByEnglishName(@PathVariable(name = "englishName") String name) {
        return identityService.getIdentityByEnglishName(name);
    }

    @GetMapping("/identities/search")
    public Result<List<IdentityResponse>> searchIdentity(@RequestParam(name = "name") String name) {
        return new Result<>(identityService.searchIdentity(name));
    }

    @GetMapping("/identities/search/sinner")
    public Result<List<IdentityResponse>> searchIdentityBySinner(@RequestParam(name = "sinner") String sinner) {
        return new Result<>(identityService.searchIdentityBySinner(sinner));
    }

    @GetMapping("/identities")
    public Result<List<IdentityResponse>> getAllIdentities() {
        return new Result<>(identityService.getAllIdentities());
    }
}
