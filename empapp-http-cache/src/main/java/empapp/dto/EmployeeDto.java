package empapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDto {

    private Long id;

    private String name;

    private Integer version;

    private List<AddressDto> addresses;
}
