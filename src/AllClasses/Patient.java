package AllClasses;

public class Patient {

    private int pId;
    private String name;
    private int age;
    private String disease;
    private String phNo;

    public Patient() {
    }

    public Patient(int pId, String name, int age, String disease, String phNo) {
        this.pId = pId;
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.phNo = phNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

}
