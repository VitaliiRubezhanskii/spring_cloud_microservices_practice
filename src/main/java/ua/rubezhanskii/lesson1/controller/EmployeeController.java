package ua.rubezhanskii.lesson1.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.rubezhanskii.lesson1.ExceptionHandlers.EmployeeNotFoundException;
import ua.rubezhanskii.lesson1.JpaRepository.EmployeeService;
import ua.rubezhanskii.lesson1.model.Employee;
import ua.rubezhanskii.lesson1.repository.EmployeeMysqlDao;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@RestController
@RequestMapping(value = "/employees")
@Api(value="employeeService", description="Operations pertaining to employees")
public class EmployeeController {

    @Autowired
    private EmployeeMysqlDao employeeMysqlDao;
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Search an employee with an empNo",response = Employee.class)
    @GetMapping(value = "/{id}", produces = "application/json")
    public Employee getOneEmployee(@PathVariable int id){
      Employee employee=employeeService.findOne(id);
      if (employee==null) throw new EmployeeNotFoundException("Employee is absent in database");


//       // To fix HATEOAS
//        Resource<Employee> resource=new Resource<Employee>(employee);
//         ControllerLinkBuilder linkBuilder=linkTo(this.getClass(),getEmployees());
//         resource.add(linkTo(linkBuilder).withRel("all-employees"));

        return employee;
    }

    @ApiOperation(value = "View a list of current employees",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping(value = "/", produces = "application/json")
    public List<Employee> getEmployees(){
        return employeeService.findEmployees();
    }

    @ApiOperation(value = "Add an employee")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {
        Employee createdEmployee=employeeService.save(employee);
                URI createdEmployeeURI= ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("{id}")
            .buildAndExpand(createdEmployee.getEmpNo())
            .toUri();
    return ResponseEntity.created(createdEmployeeURI).build();
    }
//
    @ApiOperation(value = "Update an employee")
    @PutMapping(value = "/", produces = "application/json")
    public Employee update(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @ApiOperation(value = "Delete an employee")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public Employee delete(@PathVariable  int id){
        Employee employeeToDelete=employeeService.findOne(id);
        employeeMysqlDao.delete(employeeToDelete);
        if (employeeToDelete==null) throw new EmployeeNotFoundException("Employee is absent in database");
        return employeeToDelete;

    }

}
