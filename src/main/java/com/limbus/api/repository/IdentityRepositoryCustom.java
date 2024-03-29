package com.limbus.api.repository;

import com.limbus.api.domain.identity.Identity;

import java.util.List;

public interface IdentityRepositoryCustom {

    List<Identity> findByName(String name);
}
