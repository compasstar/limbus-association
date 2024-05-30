package com.limbus.api.repository.identity;

import com.limbus.api.domain.identity.Identity;
import com.limbus.api.domain.type.Sinner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IdentityRepository extends JpaRepository<Identity, Long>, IdentityRepositoryCustom {

    //수감자별 검색
    List<Identity> findBySinner(Sinner sinner);

    //등급별 검색
    List<Identity> findByRarity(Integer rarity);

    //수감자 영문이름으로 검색
    Optional<Identity> findByEnglishName(String englishName);

}
