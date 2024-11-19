package empapp;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Duration;

@Controller
@RequestMapping("/api/counter")
@AllArgsConstructor
@Slf4j
public class CounterController {

    private final TaskExecutor taskExecutor;

    @GetMapping
    @SneakyThrows
    public SseEmitter count() {
        SseEmitter emitter = new SseEmitter();

        taskExecutor.execute(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    emitter.send("Number: " + i);
                    Thread.sleep(Duration.ofSeconds(1));
                }
                emitter.complete();
            }catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        log.info("Return emitter");

        return emitter;
    }
}
