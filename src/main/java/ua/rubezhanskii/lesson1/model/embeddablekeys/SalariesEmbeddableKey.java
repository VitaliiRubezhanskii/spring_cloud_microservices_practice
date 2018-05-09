package ua.rubezhanskii.lesson1.model.embeddablekeys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
@Embeddable
public class SalariesEmbeddableKey implements Serializable {

    private  int empNo;
    private Date fromDate;

    public SalariesEmbeddableKey() {
    }

    public SalariesEmbeddableKey(int empNo, Date fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
}
