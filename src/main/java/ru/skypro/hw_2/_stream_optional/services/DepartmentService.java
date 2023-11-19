package ru.skypro.hw_2._stream_optional.services;

import ru.skypro.hw_2._stream_optional.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalary(Integer departmentId);
    Employee getEmployeeWithMinSalary(Integer departmentId);
    List<Employee> getAllEmployeesByDepartment(Integer departmentId);
    Map<Integer, List<Employee>> getAllEmployees();
}
