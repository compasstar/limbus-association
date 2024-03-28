package com.limbus.api.repository;

import com.limbus.api.domain.identity.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityRepository extends JpaRepository<Identity, Long> {
}
