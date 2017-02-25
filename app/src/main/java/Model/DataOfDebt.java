package Model;

/**
 * Created by Dhanraj on 03-01-2017.
 */
public class DataOfDebt {

    public String reasonofdebt;
    public String Name;
    public String emailid;
    public String number;
    public String debtamount;
    public Integer parentid;
    public Integer UdhariTableId;
    public Integer Debt_keyid;
    public String radiostringvalue;
    public Integer sumofdebt;


    public String getReasonofdebt() {
        return reasonofdebt;
    }

    public void setReasonofdebt(String reasonofdebt) {
        this.reasonofdebt = reasonofdebt;
    }


    public Integer getSumofdebt() {
        return sumofdebt;
    }

    public void setSumofdebt(Integer sumofdebt) {
        this.sumofdebt = sumofdebt;
    }

    public String getRadiostringvalue() {
        return radiostringvalue;
    }

    public void setRadiostringvalue(String radiostringvalue) {
        this.radiostringvalue = radiostringvalue;
    }

    public Integer getDebt_keyid() {
        return Debt_keyid;
    }

    public void setDebt_keyid(Integer debt_keyid) {
        Debt_keyid = debt_keyid;
    }

    public Integer getUdhariTableId() {
        return UdhariTableId;
    }

    public void setUdhariTableId(Integer udhariTableId) {
        UdhariTableId = udhariTableId;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String time;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getDebtamount() {
        return debtamount;
    }

    public void setDebtamount(String debtamount) {
        this.debtamount = debtamount;
    }



}
