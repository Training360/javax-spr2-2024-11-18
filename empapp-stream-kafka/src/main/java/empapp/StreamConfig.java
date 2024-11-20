package empapp;

import empapp.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration(proxyBeanMethods = false)
@Slf4j
public class StreamConfig {

    @Bean
    public Consumer<EmployeeDto> handle() {
        return employee -> log.info("Employee has been created {} {}", employee.getId(), employee.getName());
    }
}
