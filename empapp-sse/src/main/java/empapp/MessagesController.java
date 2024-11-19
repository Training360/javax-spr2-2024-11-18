package empapp;

import empapp.dto.Message;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.yaml.snakeyaml.emitter.Emitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@RequestMapping("/api/employees/messages")
public class MessagesController {

    private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping
    public SseEmitter getMessages() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        return emitter;
    }

    @EventListener
    public void handleMessage(Message message) {
        List<SseEmitter> emittersToDelete = new ArrayList<>();
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .id(UUID.randomUUID().toString())
                        .name("message")
                        .comment("this is a created event message")
                        .data(message)
                );
            } catch (Exception e) {
                emittersToDelete.add(emitter);
            }
        }
        emitters.removeAll(emittersToDelete);
    }
}
