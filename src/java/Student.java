import java.sql.Date;

public class Student {

    public Student(int id, String name, Date dob, String department) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.department = department;
    }
    private int id;
    private String name;
    private Date dob;
    private String department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    
}
