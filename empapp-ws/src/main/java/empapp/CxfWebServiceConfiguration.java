package empapp;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class CxfWebServiceConfiguration {

    @Bean
    public Endpoint employeeWebServiceEndpoint(Bus bus, EmployeeWebService employeeWebService) {
        Endpoint endpoint = new EndpointImpl(bus, employeeWebService);
        endpoint.publish("/employees");
        return endpoint;
    }
}
