package ru.otus.java.pro.mt.core.transfers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Страница с переводами")
public record TransfersPageDto(
        @Schema(description = "Список переводов") List<TransferDto> entries
) {}