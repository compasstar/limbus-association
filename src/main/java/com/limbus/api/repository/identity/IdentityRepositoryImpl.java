package com.limbus.api.repository.identity;

import com.limbus.api.domain.identity.Identity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.limbus.api.domain.identity.QIdentity.identity;

public class IdentityRepositoryImpl implements IdentityRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public IdentityRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Identity> findByPartName(String name) {
        return queryFactory
                .selectFrom(identity)
                .where(identity.name.contains(name))
                .fetch();
    }
}
