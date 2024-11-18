package empapp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/counter")
@AllArgsConstructor
@Slf4j
public class CounterController {

    private final CounterService counterService;

    @PostMapping
    // https://httpgoats.com/202
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void count() {
        log.info("Proxy class: {}", counterService.getClass().getName());
        counterService.count();
    }
}
