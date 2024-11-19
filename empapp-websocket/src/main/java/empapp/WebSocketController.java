package empapp;

import empapp.dto.RequestMessage;
import empapp.dto.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/messages")
    @SendTo("/topic/employees")
    public ResponseMessage handle(RequestMessage request) {
        return new ResponseMessage("Reply to: " + request.requestText());
    }
}
