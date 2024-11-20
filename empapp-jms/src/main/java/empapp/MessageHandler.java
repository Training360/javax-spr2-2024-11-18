package empapp;

import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageHandler {

    @JmsListener(destination = "employees-queue")
    @SneakyThrows
    public void receiveMessage(Message message) {
        if (message instanceof TextMessage textMessage) {
            log.info("Message received: {}", textMessage.getText());
        }
        else {
            log.error("Unknown message received: {}", message);
        }
    }
}
