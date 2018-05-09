package ua.rubezhanskii.lesson1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.rubezhanskii.lesson1.model.embeddablekeys.SalariesEmbeddableKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "salaries")
@IdClass(SalariesEmbeddableKey.class)
public class Salary implements Serializable {
    @JsonIgnore
    private int empNo;
    private int salary;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fromDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date toDate;
    @JsonIgnore
    private Employee employee;

    public Salary() {
    }

    public Salary(int empNo, int salary, Date fromDate, Date toDate) {
        this.empNo = empNo;
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "empNo",column = @Column(name = "emp_no")),
            @AttributeOverride(name = "fromDate",column = @Column(name = "from_date"))
    })
    @Column(name = "emp_no")
    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    @Column(name="salary")
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    @Column(name="from_date")
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Column(name = "to_date")
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no",updatable = false,insertable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "empNo=" + empNo +
                ", salary=" + salary +
                ", dromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
