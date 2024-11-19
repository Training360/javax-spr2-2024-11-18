package empapp;

import empapp.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggerService {

    @EventListener
    public void logResponseMessage(ResponseMessage message) {
        log.info("Message has come: {}", message.responseText());
    }
}
