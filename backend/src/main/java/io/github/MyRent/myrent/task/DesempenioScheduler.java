package io.github.MyRent.myrent.task;

import io.github.MyRent.myrent.service.IDesempenioService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DesempenioScheduler {
    private final IDesempenioService desempenioService;

    public DesempenioScheduler(IDesempenioService desempenioService) {
        this.desempenioService = desempenioService;
    }

    @Scheduled(cron = "0 59 23 L * ?")
    public void generateDesempenios() {
        desempenioService.generateDesempenios();
    }
}
