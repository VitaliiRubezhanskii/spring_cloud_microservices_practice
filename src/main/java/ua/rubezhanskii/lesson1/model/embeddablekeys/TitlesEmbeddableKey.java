package ua.rubezhanskii.lesson1.model.embeddablekeys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class TitlesEmbeddableKey implements Serializable {

    private int empNo;
    private String title;
    private Date fromDate;

    public TitlesEmbeddableKey() {
    }

    public TitlesEmbeddableKey(int empNo, String title, Date fromDate) {
        this.empNo = empNo;
        this.title = title;
        this.fromDate = fromDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public String toString() {
        return "TitlesEmbeddableKey{" +
                "empNo=" + empNo +
                ", title='" + title + '\'' +
                ", fromDate=" + fromDate +
                '}';
    }
}
