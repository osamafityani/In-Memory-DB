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
        if (name.length() <= 15) {
            this.name = name;
        }else{
            System.out.println("The value you entered is too long...");
        }
    }

    public synchronized void setMajor(String major) {
        if (major.length() <= 15) {
            this.major = major;
        }else{
            System.out.println("The value you entered is too long...");
        }
    }

    public synchronized void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setId(int id) {
        this.id = id;
        objectsCounter--;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() + " | " + "Name: " + this.getName() + " | " + "Major: " + this.getMajor() + " | " + "GPA: " + this.getGpa();
    }
}
