package ua.rubezhanskii.lesson1.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;
import ua.rubezhanskii.lesson1.ExceptionHandlers.EmployeeNotFoundException;
import ua.rubezhanskii.lesson1.model.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Repository
public class EmployeeMysqlDao implements EmpRepositoryI {

@Autowired
private JdbcTemplate jdbcTemplate;

    @Override
    public Employee findOne(int id) {
        Employee foundEmployee=null;
        final String GET_ONE_EMPLOYEE="select * from employees where emp_no=?";
        try {
             foundEmployee = (Employee) jdbcTemplate.queryForObject(GET_ONE_EMPLOYEE, new Object[]{id}, new EmployeeRowMapper());
        }catch (DataAccessException ex){
            throw new EmployeeNotFoundException("Employee is absent in database");
        }
        return foundEmployee;
    }

    @Override
    public List<Employee> findEmployees() {
        final String GET_ALL_EMPLOYEES="select * from employees";
        return jdbcTemplate.query(GET_ALL_EMPLOYEES,new EmployeeRowMapper());
    }

    @Override
    public Employee save(Employee employee) {
        final String INSERT_EMPLOYEE="insert into employees(emp_no,first_name,last_name,birth_date,gender,hire_date)" +
                "values(?,?,?,?,?,?)";
        jdbcTemplate.update(INSERT_EMPLOYEE,employee.getEmpNo(),employee.getFirstName(),employee.getLastName(),
        employee.getBirthDate(),employee.getGender(),employee.getHireDate());
        return employee;
    }

    @Override
    public Employee delete(Employee employee) {
        Employee deletedEmployee=findOne(employee.getEmpNo());
        final String DELETE_EMPLOYEE="delete from employees where emp_no=?";
        jdbcTemplate.update(DELETE_EMPLOYEE,employee.getEmpNo());
        return deletedEmployee;
    }

    @Override
    public Employee update(Employee employee) {
        final String UPDATE_EMPLOYEE="update employees set first_name=?,last_name=?,birth_date=?," +
                "gender=?,hire_date=? WHERE emp_no=?";
       jdbcTemplate.update(UPDATE_EMPLOYEE,employee.getFirstName(),employee.getLastName(),employee.getBirthDate(),
               employee.getGender(),employee.getHireDate(),employee.getEmpNo());
        return employee;
    }

    @Override
    public void populateData() {
     //   String out=inserts("src/main/resources/dataDump.sql").toString();
       // System.out.println(out);
    }

    private List<String> inserts(String path){

        StringBuilder stringBuilder=new StringBuilder();
        StringTokenizer tokenizer=null;
        List<String> queries=null;
        try {
            FileInputStream fileInputStream=new FileInputStream(new File(path));

           int chars;

           while ((chars=fileInputStream.read())!=-1){
               stringBuilder.append((char)chars);

           }

           String longQuery=stringBuilder.toString();
              tokenizer=new StringTokenizer(longQuery,";");
                    queries=new ArrayList<>(tokenizer.countTokens());

           while (tokenizer.hasMoreTokens()){
              queries.add(tokenizer.nextToken());
           }
            queries.forEach(query ->{

                jdbcTemplate.update(query);
            });

        } catch ( IOException  e) {
            e.printStackTrace();
        }
        return queries;
    }

  @Override
    public void destroyData() {
      //  jdbcTemplate.update("delete from employees WHERE employees.emp_no=1");
    }

    private class EmployeeRowMapper implements RowMapper{

        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
           Employee employee=new Employee();
           employee.setEmpNo(resultSet.getInt("emp_no"));
           employee.setFirstName(resultSet.getString("first_name"));
           employee.setLastName(resultSet.getString("last_name"));
           employee.setBirthDate(resultSet.getString("birth_date"));
           employee.setGender(resultSet.getString("gender"));
           employee.setHireDate(resultSet.getString("hire_date"));
           return employee;

        }
    }
}
