package com.limbus.api.repository;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.Sinner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdentityRepository extends JpaRepository<Identity, Long>, IdentityRepositoryCustom {


    //수감자별 검색
    List<Identity> findBySinner(Sinner sinner);

    //등급별 검색
    List<Identity> findByRarity(Integer rarity);
}
