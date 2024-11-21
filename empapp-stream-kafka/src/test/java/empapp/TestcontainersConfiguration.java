package empapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@TestConfiguration(proxyBeanMethods = false)
@Slf4j
@Testcontainers
public class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer() {
        var container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
                .withExposedPorts(5432)
                .withDatabaseName("employees")
                .withUsername("employees")
                .withPassword("employees")
                .withReuse(true);
        container.setPortBindings(List.of("5432:5432"));
        return container;
    }

    @Bean
    public KafkaContainer kafkaContainer() {
        KafkaContainer container = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.7.1"))
                .withKraft()
                .withReuse(true);
        container.setPortBindings(List.of("9093:9093"));
        return container;
    }

}
