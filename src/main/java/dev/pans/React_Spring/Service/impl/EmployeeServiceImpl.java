package dev.pans.React_Spring.Service.impl;

import dev.pans.React_Spring.Entities.Employee;
import dev.pans.React_Spring.Repository.EmployeeRepository;
import dev.pans.React_Spring.Service.EmployeeService;
import dev.pans.React_Spring.dto.EmployeeDto;
import dev.pans.React_Spring.exception.ResourceNotFoundExceptiom;
import dev.pans.React_Spring.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee saveEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(saveEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(long employeeId) {
       Employee employee =  employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundExceptiom("Employee not exists with that id" + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees =  employeeRepository.findAll();
    return employees.stream().map(EmployeeMapper::mapToEmployeeDto)
            .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

   Employee employee =  employeeRepository.findById(employeeId).orElseThrow(()
            -> new ResourceNotFoundExceptiom("Employee not exists with that id" + employeeId));

   employee.setFirstName(updatedEmployee.getFirstName());
   employee.setLastName(updatedEmployee.getLastName());
   employee.setEmail(updatedEmployee.getEmail());
   employeeRepository.save(employee);

   Employee updatedObject = employeeRepository.save(employee);
   return EmployeeMapper.mapToEmployeeDto(updatedObject);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee =  employeeRepository.findById(employeeId).orElseThrow(()
                -> new ResourceNotFoundExceptiom("Employee not exists with that id" + employeeId));

        employeeRepository.deleteById(employeeId);
    }


}
