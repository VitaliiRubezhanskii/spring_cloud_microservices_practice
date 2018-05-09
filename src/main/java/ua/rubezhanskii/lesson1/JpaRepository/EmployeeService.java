package ua.rubezhanskii.lesson1.JpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rubezhanskii.lesson1.model.Employee;
import ua.rubezhanskii.lesson1.repository.EmpRepositoryI;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class EmployeeService  {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmpRepositoryI empRepositoryI;


    public Employee findOne(int id){
       return employeeRepository.getOne(id);
    }

   public List<Employee> findEmployees(){
       return  employeeRepository.findAll();
    }

   public Employee save(Employee employee){
        return  employeeRepository.save(employee);
    }

   public Employee delete(Employee employee){
        Employee deletedEmployee=employeeRepository.getOne(employee.getEmpNo());
        employeeRepository.delete(employee);
        return deletedEmployee;
    }


//    @PostConstruct
//    public void populateDatabase(){
//        System.out.println("-----------Data are being populated------------");
//       empRepositoryI.populateData();
//    }
//
//    @PreDestroy
//    public void destroyData(){
//        System.out.println("-----------Data are being destroyed------------");
//        empRepositoryI.destroyData();
//    }

}
