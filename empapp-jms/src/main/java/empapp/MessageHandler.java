package empapp;

import empapp.dto.EmployeeHasBeenCreatedMessage;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.jms.client.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageHandler {

    @JmsListener(destination = "employees-queue")
    public void receiveMessage(EmployeeHasBeenCreatedMessage message) {
        log.info("Employee has been created: {} {}", message.id(), message.name());
    }
}
