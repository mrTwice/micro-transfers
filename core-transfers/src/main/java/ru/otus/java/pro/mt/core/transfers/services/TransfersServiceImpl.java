package ru.otus.java.pro.mt.core.transfers.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.otus.java.pro.mt.avro.StatusInfoDto;
import ru.otus.java.pro.mt.core.transfers.configs.properties.TransfersProperties;
import ru.otus.java.pro.mt.core.transfers.dtos.ExecuteTransferDtoRq;
import ru.otus.java.pro.mt.core.transfers.entities.Transfer;
import ru.otus.java.pro.mt.core.transfers.exceptions_handling.BusinessLogicException;
import ru.otus.java.pro.mt.core.transfers.kafka.MessageService;
import ru.otus.java.pro.mt.core.transfers.metrics.TransfersMetricsService;
import ru.otus.java.pro.mt.core.transfers.repositories.TransfersRepository;
import ru.otus.java.pro.mt.core.transfers.validators.TransferRequestValidator;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransfersServiceImpl implements TransfersService {
    private final TransfersRepository transfersRepository;
    private final TransferRequestValidator transferRequestValidator;
    private final TransfersProperties transfersProperties;
    private final LimitsServiceImpl limitsService;
    private final MessageService<StatusInfoDto> statusProducer;
    private final TransfersMetricsService transfersMetricsService;

    @Override
    public Optional<Transfer> getTransferById(String id, String clientId) {
        return transfersRepository.findByIdAndClientId(id, clientId);
    }

    @Override
    public Page<Transfer> getAllTransfers(String clientId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return transfersRepository.findAllByClientId(clientId, pageable);
    }

    @Override
    public void execute(String clientId, ExecuteTransferDtoRq executeTransferDtoRq) {
        transfersMetricsService.incrementTransferRequests();
        try{
            transferRequestValidator.validate(executeTransferDtoRq);
            if (!limitsService.isLimitEnough(clientId, executeTransferDtoRq.getAmount())) {
                // ...
            }
            if (executeTransferDtoRq.getAmount().compareTo(transfersProperties.getMaxTransferSum()) > 0) {
                throw new BusinessLogicException("OOPS", "OOPS_CODE");
            }
            Transfer transfer = new Transfer(UUID.randomUUID().toString(), "1", "2", "1", "2", "Demo", BigDecimal.ONE);
            save(transfer);
            statusProducer.send(new StatusInfoDto(transfer.getId(), "EXECUTED"));
            transfersMetricsService.incrementSuccessfulTransfers();
        } catch (Exception e) {
            transfersMetricsService.incrementFailedTransfers();
            throw e;
        }
    }

    @Override
    public void save(Transfer transfer) {
        transfersRepository.save(transfer);
    }
}
