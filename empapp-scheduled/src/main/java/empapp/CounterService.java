package empapp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class CounterService {

    @SneakyThrows
    @Async
    public void count() {
        for (int i = 0; i < 10; i++) {
            log.info("Counting {}", i);
            Thread.sleep(Duration.ofSeconds(1));
        }
    }

    @Scheduled(fixedRate = 3000)
    public void log() {
        log.info("Scheduled job");
    }
}
