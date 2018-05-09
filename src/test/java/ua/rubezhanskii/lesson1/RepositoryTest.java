package ua.rubezhanskii.lesson1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.rubezhanskii.lesson1.JpaRepository.EmployeeService;
import ua.rubezhanskii.lesson1.configs.Configa;
import ua.rubezhanskii.lesson1.model.Employee;

import java.util.Arrays;
import java.util.List;



import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = Lesson1Application.class)
public class RepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void  findOne_Should_Return_One_Employee(){
        Employee savedEmployee=new Employee(6, "Vitalii", "Rubezhanskii", "1992-03-16", "M", "2018-04-12");
        testEntityManager.persist(savedEmployee);
        testEntityManager.flush();
        Employee foundEmployee=employeeService.findOne(savedEmployee.getEmpNo());
        assertThat(savedEmployee).isEqualTo(foundEmployee);
        assertThat(foundEmployee).isNotNull();
    }


    @Test
    public void findAll_Should_Return_More_Then_One_Employee(){

        List<Employee> savedEmployees= Arrays.asList(
        new Employee(1, "Dmitry", "Petrov", "1993-10-21", "M", "2015-01-17"),
        new Employee(2, "Vitalii", "Rubezhanskii", "1992-03-16", "M", "2018-04-12"),
        new Employee(3, "Olga", "Korzh", "1990-13-15", "F", "2010-10-03")
        );

        savedEmployees.forEach(employee -> {
            testEntityManager.persist(employee);
        });

        List<Employee>foundEmployees=employeeService.findEmployees();
        assertThat(foundEmployees).isNotNull();
        assertThat(foundEmployees).doesNotContainNull();
        assertThat(foundEmployees).isNotEmpty();
        assertThat(savedEmployees).isEqualTo(foundEmployees);
        assertThat(savedEmployees.size()).isEqualTo(3);
    }

    @Test
    public void save_Should_Save_Employee(){
        Employee employee=new Employee(1, "Dmitry", "Petrov", "1993-10-21", "M", "2015-01-17");
        Employee savedEmployee=employeeService.save(employee);
        assertThat(employee.getEmpNo()).isEqualTo(savedEmployee.getEmpNo());
        assertThat(employee.getFirstName()).isEqualTo(savedEmployee.getFirstName());
        assertThat(employee.getLastName()).isEqualTo(savedEmployee.getLastName());
        assertThat(employee.getBirthDate()).isEqualTo(savedEmployee.getBirthDate());
        assertThat(employee.getGender()).isEqualTo(savedEmployee.getGender());
        assertThat(employee.getHireDate()).isEqualTo(savedEmployee.getHireDate());
    }

    @Test
    public void delete_Shoud_Delete_Employee(){
        Employee employee=new Employee(1, "Dmitry", "Petrov", "1993-10-21", "M", "2015-01-17");
        testEntityManager.persist(employee);
        employeeService.delete(employee);
        assertThat(testEntityManager.find(Employee.class,1)).isNull();
    }



}
