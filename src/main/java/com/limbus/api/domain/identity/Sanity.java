package com.limbus.api.domain.identity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor
public class Sanity {

    @ElementCollection
    private List<String> panic;

    @ElementCollection
    private List<String> factorsIncreasingSanity;

    @ElementCollection
    private List<String> factorsDecreasingSanity;

    @Builder
    public Sanity(List<String> panic, List<String> factorsIncreasingSanity, List<String> factorsDecreasingSanity) {
        this.panic = panic;
        this.factorsIncreasingSanity = factorsIncreasingSanity;
        this.factorsDecreasingSanity = factorsDecreasingSanity;
    }
}
