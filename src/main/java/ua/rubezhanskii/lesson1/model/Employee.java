package ua.rubezhanskii.lesson1.model;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    private int empNo;
    @Size(min = 2, message = "first name should have at least 2 characters")
    private String firstName;
    @Size(min = 2,message = "last name should have at least 2 characters")
    private String lastName;
    private String birthDate;
    private String gender;
    private String hireDate;

    private List<Salary> salary;
    private List<Jobtitle> jobtitle;

    public Employee() {
    }

    public Employee(int empNo, @Size(min = 2, message = "first name should have at least 2 characters") String firstName,
                    @Size(min = 2, message = "last name should have at least 2 characters") String lastName,
                    String birthDate, String gender, String hireDate) {

        this.empNo = empNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.hireDate = hireDate;
    }



    @Id
    @Column(name="emp_no")
    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    @Column(name="first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="birth_date")
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name="gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name="hire_date")
    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<Salary> getSalary() {
        return salary;
    }

    public void setSalary(List<Salary> salary) {
        this.salary = salary;
    }

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true

    )
    public List<Jobtitle> getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(List<Jobtitle> jobtitle) {
        this.jobtitle = jobtitle;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }
}
