package com.limbus.api.domain.identity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Sanity {

    private String panic;
    private String factorsIncreasingSanity;
    private String factorsDecreasingSanity;
}
