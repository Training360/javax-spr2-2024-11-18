package empapp;

import empapp.wsdto.EmployeeWsDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static empapp.EmployeeWebService.EMPLOYEES_NAMESPACE;

@WebService(targetNamespace = EMPLOYEES_NAMESPACE)
@Service
@AllArgsConstructor
public class EmployeeWebService {

    public static final String EMPLOYEES_NAMESPACE = "https://training360.com/employees";

    private final EmployeeService employeeService;

    @WebMethod
    @WebResult(name = "employee", targetNamespace = EMPLOYEES_NAMESPACE)
    public List<EmployeeWsDto> findAllEmployees() {
        return employeeService.listEmployees()
                .stream().map(e -> new EmployeeWsDto(e.getId(), e.getName()))
                .toList();
    }
}
