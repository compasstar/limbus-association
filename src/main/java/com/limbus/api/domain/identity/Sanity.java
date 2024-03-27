package com.limbus.api.domain.identity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Getter;

@Embeddable
@Getter
public class Sanity {

    @Lob
    private String panic;

    @Lob
    private String factorsIncreasingSanity;

    @Lob
    private String factorsDecreasingSanity;
}
