package com.lucashthiele.milhas.domain.destination;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Label;

import java.math.BigDecimal;

public record DestinationDTO(
        @NotNull
        String name,
        @NotNull
        BigDecimal value,
        @NotNull
        String pictureUrl,
        @NotNull
        String secondaryPictureUrl,
        @NotNull
        @Size(max = 160)
        String metaText,
        String description) {
}
