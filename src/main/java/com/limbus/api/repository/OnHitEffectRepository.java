package com.limbus.api.repository;

import com.limbus.api.domain.skill.OnHitEffect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnHitEffectRepository extends JpaRepository<OnHitEffect, Long> {
}
