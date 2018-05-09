package ua.rubezhanskii.lesson1.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.rubezhanskii.lesson1.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


}
