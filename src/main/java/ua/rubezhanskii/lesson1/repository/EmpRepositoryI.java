package ua.rubezhanskii.lesson1.repository;

import ua.rubezhanskii.lesson1.model.Employee;

import java.util.List;

public interface EmpRepositoryI {

    Employee findOne(int id);
    List<Employee> findEmployees();
    Employee save(Employee employee);
    Employee delete(Employee employee);
    Employee update(Employee employee);

    void populateData();
    void destroyData();
}
