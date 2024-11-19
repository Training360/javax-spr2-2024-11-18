package training.empappwebsocketclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;

import java.lang.reflect.Type;

@Slf4j
public class MyStompMessageHandler implements StompSessionHandler {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("Connected");
        session.subscribe("/topic/employees", this);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("error", exception);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.error("error", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        switch (payload) {
            case Message(var text) -> log.info("received message: {}", text);
            default -> log.error("unexpected payload: {}", payload);
        }
    }
}
