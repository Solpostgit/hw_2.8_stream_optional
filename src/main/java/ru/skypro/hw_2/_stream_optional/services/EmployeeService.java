package ru.skypro.hw_2._stream_optional.services;

import ru.skypro.hw_2._stream_optional.Employee;

import java.util.Map;

public interface EmployeeService {
    Employee add(String firstName, String lastName, Integer salary, Integer department);
    Employee find(String firstName, String lastName);
    Employee remove(String firstName, String lastName);
    Map<String, Employee> getAll();
}
