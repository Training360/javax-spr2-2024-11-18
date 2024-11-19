package empapp;

import empapp.dto.RequestMessage;
import empapp.dto.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@AllArgsConstructor
public class WebSocketController {

    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public ResponseMessage handle(RequestMessage request) {
        log.info("Message received: {}", request);
        return new ResponseMessage("Reply to: " + request.requestText());
    }

    @EventListener
    public void sendMessage(ResponseMessage responseMessage) {
        messagingTemplate.convertAndSend("/topic/employees", responseMessage);
    }
}
