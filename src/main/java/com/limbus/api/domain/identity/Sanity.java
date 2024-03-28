package com.limbus.api.domain.identity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Sanity {

    @Lob
    private String panic;

    @Lob
    private String factorsIncreasingSanity;

    @Lob
    private String factorsDecreasingSanity;

    @Builder
    public Sanity(String panic, String factorsIncreasingSanity, String factorsDecreasingSanity) {
        this.panic = panic;
        this.factorsIncreasingSanity = factorsIncreasingSanity;
        this.factorsDecreasingSanity = factorsDecreasingSanity;
    }
}
