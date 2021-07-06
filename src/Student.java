public class Student {

    private static int objectsCounter = 1;

    private int id;
    private String name;
    private String major;
    private double gpa;

    public Student(String name, String major, double gpa){
        this.id = objectsCounter;
        this.name = name;
        this.major = major;
        this.gpa = gpa;

        objectsCounter++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public double getGpa() {
        return gpa;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
