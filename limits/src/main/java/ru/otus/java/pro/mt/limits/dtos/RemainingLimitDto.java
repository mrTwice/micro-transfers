package ru.otus.java.pro.mt.limits.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "Доступный лимит")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemainingLimitDto {

    @Schema(
            description = "Доступный лимит",
            example = "100.00",
            required = true
    )
    private BigDecimal remainingLimit;
}
