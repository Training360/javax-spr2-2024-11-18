package empapp;

import empapp.dto.EmployeeDto;
import empapp.dto.ResponseMessage;
import empapp.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    private ApplicationEventPublisher eventPublisher;

    public EmployeeDto createEmployee(EmployeeDto command) {
        Employee employee = employeeMapper.toEmployee(command);
        employeeRepository.save(employee);

        EmployeeDto employeeDto = employeeMapper.toEmployeeDto(employee);
        ResponseMessage responseMessage = new ResponseMessage(
                "Employee %s has been created with id: %d".formatted(employeeDto.getName(), employeeDto.getId()));

        eventPublisher.publishEvent(responseMessage);

        return employeeDto;
    }

    public List<EmployeeDto> listEmployees() {
        return employeeMapper.toEmployeesDto(employeeRepository.findAllWithAddresses());
    }

    public EmployeeDto findEmployeeById(long id) {
        return employeeMapper.toEmployeeDto(employeeRepository.findByIdWithAddresses(id)
                        .orElseThrow(notFoundException(id)));
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, EmployeeDto command) {
        Employee employeeToModify = employeeRepository
                .findById(id)
                .orElseThrow(notFoundException(id));
        employeeToModify.setName(command.getName());
        return employeeMapper.toEmployeeDto(employeeToModify);
    }

    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findByIdWithAddresses(id)
                .orElseThrow(notFoundException(id));
        employeeRepository.delete(employee);
    }

    private static Supplier<NotFoundException> notFoundException(long id) {
        return () -> new NotFoundException("Employee not found with id: " + id);
    }

}