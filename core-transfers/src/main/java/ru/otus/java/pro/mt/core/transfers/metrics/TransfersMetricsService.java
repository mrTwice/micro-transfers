package ru.otus.java.pro.mt.core.transfers.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class TransfersMetricsService {
    private final Counter transferRequestsCounter;
    private final Counter successfulTransfersCounter;
    private final Counter failedTransfersCounter;

    public TransfersMetricsService(MeterRegistry registry) {
        transferRequestsCounter = Counter.builder("transfers.requests")
                .description("Number of transfer requests received")
                .register(registry);

        successfulTransfersCounter = Counter.builder("transfers.successful")
                .description("Number of successful transfers")
                .register(registry);

        failedTransfersCounter = Counter.builder("transfers.failed")
                .description("Number of failed transfers")
                .register(registry);
    }

    public void incrementTransferRequests() {
        transferRequestsCounter.increment();
    }

    public void incrementSuccessfulTransfers() {
        successfulTransfersCounter.increment();
    }

    public void incrementFailedTransfers() {
        failedTransfersCounter.increment();
    }
}