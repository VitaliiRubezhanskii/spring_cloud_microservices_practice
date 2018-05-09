package ua.rubezhanskii.lesson1;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import ua.rubezhanskii.lesson1.JpaRepository.EmployeeService;
import ua.rubezhanskii.lesson1.configs.BeanAppAware;
import ua.rubezhanskii.lesson1.model.Employee;
import ua.rubezhanskii.lesson1.repository.EmpRepositoryI;
import ua.rubezhanskii.lesson1.repository.EmployeeMysqlDao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@SpringBootApplication
@EnableAutoConfiguration
public class Lesson1Application  {


	public static void main(String[] args) {
		SpringApplication.run(Lesson1Application.class);


	}


@Bean
public EmpRepositoryI empRepositoryI(){
		return  new EmployeeMysqlDao();
}
	@Bean
	public EmployeeService employeeService(){
		return new EmployeeService();
	}
	@Bean
	public CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor(){
		return new CommonAnnotationBeanPostProcessor();
	}

	@Bean
	public BeanAppAware beanAppAware(){
		return new BeanAppAware();
	}

	@Bean
	public  ConfigurationClassPostProcessor configurationClassPostProcessor(){
		return new ConfigurationClassPostProcessor();
	}




}


		/*	Employee employee = new Employee(6, "Vitalii", "Rubezhanskii", "1992-03-16", "M", "2018-04-12");
			Employee employeeForUpdate = new Employee(6, "Inga", "Panych", "1985-03-05", "F", "2005-05-17");
			System.out.println("--Print One Employee--");
			System.out.println(employeeService.findOne(10001));
			System.out.println("---Add new employee--");
			System.out.println(employeeService.save(employee));
			//System.out.println("--Update Employee--");
			//System.out.println(empRepositoryI.update(employeeForUpdate));
			//System.out.println("--Print All Employees--");
			//System.out.println(empRepositoryI.findEmployees());
			System.out.println("---Delete employee--");
			System.out.println(employeeService.delete(employee));
*/









