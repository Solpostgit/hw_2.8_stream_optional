package ru.skypro.hw_2._stream_optional.services.impl;

import org.springframework.stereotype.Service;
import ru.skypro.hw_2._stream_optional.Employee;
import ru.skypro.hw_2._stream_optional.exception.EmployeeAlreadyAddedException;
import ru.skypro.hw_2._stream_optional.exception.EmployeeNotFoundException;
import ru.skypro.hw_2._stream_optional.exception.EmployeeStorageIsFullException;
import ru.skypro.hw_2._stream_optional.services.EmployeeService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int STORAGE_SIZE = 5;
    private final Map<String, Employee> employees = new HashMap<>();
    @Override
    public Employee add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= STORAGE_SIZE) {
            throw new EmployeeStorageIsFullException("Не можем добавить сотрудника! Хранилище уже полное.");
        }

        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + firstName + "и фамилией " + lastName + " уже имеется в хранилище!");
        }

        Employee employee = new Employee(firstName, lastName, salary, department);
        employees.put(getKey(employee), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        //Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден в хранилище!");
        }

        return employees.remove(getKey(firstName, lastName));
    }

    @Override
    public Employee find(String firstName, String lastName) {

        Employee employee = employees.get(getKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " " + "не найден в хранилище!");
            }

        return employee;
    }

    @Override
    public Map<String, Employee> getAll() {
        return Collections.unmodifiableMap(employees);
    }

    private static String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    private static String getKey(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }
}
