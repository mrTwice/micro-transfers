package ru.otus.java.pro.mt.core.transfers.services;

import org.springframework.data.domain.Page;
import ru.otus.java.pro.mt.core.transfers.dtos.ExecuteTransferDtoRq;
import ru.otus.java.pro.mt.core.transfers.entities.Transfer;
import java.util.List;
import java.util.Optional;

public interface TransfersService {
    Optional<Transfer> getTransferById(String id, String clientId);
    Page<Transfer> getAllTransfers(String clientId, int page, int size);
    void execute(String clientId, ExecuteTransferDtoRq executeTransferDtoRq);
    void save(Transfer transfer);
}
