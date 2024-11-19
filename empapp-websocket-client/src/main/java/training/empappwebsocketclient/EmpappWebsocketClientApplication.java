package training.empappwebsocketclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.time.Duration;
import java.util.List;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class EmpappWebsocketClientApplication implements CommandLineRunner {

	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(EmpappWebsocketClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Hello Command Line Spring");
		log.info("JSON: {}", objectMapper.writeValueAsString(new Request("hello")));

		var sockJsClient = new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient())));
		var stompClient = new WebSocketStompClient(sockJsClient);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		var future = stompClient.connectAsync("ws://localhost:8080/websocket-endpoint", new MyStompMessageHandler());

		future.thenAcceptAsync(session -> {
			log.info("Send messages");
			for (int i = 0; i < 10; i++) {
				session.send("/app/messages", new Request("John Doe " + i));
			}
		});

		log.info("after connect");

		Thread.sleep(Duration.ofHours(1));
	}
}
