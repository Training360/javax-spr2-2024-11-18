package empapp;

import empapp.dto.RequestMessage;
import empapp.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public ResponseMessage handle(RequestMessage request) {
        log.info("Message received: {}", request);
        return new ResponseMessage("Reply to: " + request.requestText());
    }
}
