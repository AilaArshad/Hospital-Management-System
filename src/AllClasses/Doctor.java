package AllClasses;

public class Doctor {

    private int dId;
    private String name;
    private String qualification;
    private String designation;
    private int salary;
    private String department;

    public Doctor() {
    }

    public Doctor(int dId, String name, String qualification, String designation, int salary, String department) {
        this.dId = dId;
        this.name = name;
        this.qualification = qualification;
        this.designation = designation;
        this.salary = salary;
        this.department = department;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
