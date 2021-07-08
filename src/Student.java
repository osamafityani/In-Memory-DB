public class Student {

    public static int objectsCounter = 0;

    private int id;
    private String name;
    private String major;
    private double gpa;

    public Student(){
        objectsCounter++;
        this.id = objectsCounter;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized String getMajor() {
        return major;
    }

    public synchronized double getGpa() {
        return gpa;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized void setMajor(String major) {
        this.major = major;
    }

    public synchronized void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public synchronized void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + " | " + "Name: " + this.name + " | " + "Major: " + this.major + " | " + "GPA: " + this.gpa;
    }
}
