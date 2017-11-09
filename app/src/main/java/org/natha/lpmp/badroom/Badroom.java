package org.natha.lpmp.badroom;

/**
 * Created by myssd on 11/4/17.
 */
import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Badroom {
    @Element
    private String no;
    @Element
    private String noKamar;
    @Element
    private String regNo;
    @Element
    private String isUsed;
    @Element
    private Date startDate;
    @Element
    private Date endDate;

    public Badroom() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(String noKamar) {
        this.noKamar = noKamar;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
