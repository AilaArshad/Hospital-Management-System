package AllClasses;

import java.sql.*;

public class Appointment {

    private int aId;
    private String pName;
    private String dName;
    private Timestamp date_time;
    private String app_type;

    public Appointment() {
    }

    public Appointment(int aId, String pName, String dName, Timestamp date_time, String app_type) {
        this.aId = aId;
        this.pName = pName;
        this.dName = dName;
        this.date_time = date_time;
        this.app_type = app_type;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

}
