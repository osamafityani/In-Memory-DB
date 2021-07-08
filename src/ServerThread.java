import java.io.*;
import java.net.*;
import java.util.List;


class ServerThread extends Thread {

    Socket clientSocket;
    ServerThread(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    StudentService studentService = new StudentService();

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;

        try{

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String userInput;



            while(true){
                printUI(out);
                userInput = in.readLine();
                System.out.println("Sent from the client" + clientSocket + " | Message: " + userInput);

                switch (Integer.parseInt(userInput)){
                    case 1:
                        createStudent(in, out);
                        break;
                    case 2:
                        selectStudent(in, out);
                        break;
                    case 3:
                        updateStudent(in, out);
                        break;
                    case 4:
                        deleteStudent(in, out);
                        break;
                    default:
                        break;
                }


                out.println("\n\nPress any key to continue...");
                in.readLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    void printUI(PrintWriter out){
        out.println("Please choose one of the following operations:");
        out.println("1. Create");
        out.println("2. Read");
        out.println("3. Update");
        out.println("4. Delete");
    }

    void createStudent(BufferedReader in, PrintWriter out) throws IOException{
        out.println("Student's Name:");
        String name = in.readLine();

        out.println("Student's Major:");
        String major = in.readLine();

        out.println("Student's GPA:");
        double gpa = Double.parseDouble(in.readLine());

        studentService.insertStudent(new Student(name, major, gpa));
        out.println("Student added successfully");
        out.println("=========================================");
    }
    void selectStudent(BufferedReader in, PrintWriter out)throws IOException{
        out.println("Choose one of the following operations:");
        out.println("1. Select student by Id");
        out.println("2. Select all students");

        int userInput = Integer.parseInt(in.readLine());
        switch (userInput){
            case 1:
                selectStudentById(in, out);
                break;
            case 2:
                selectAllStudents(out);
                break;
        }
    }
    void selectStudentById(BufferedReader in, PrintWriter out) throws IOException{
        out.println("Student Id: ");
        int id = Integer.parseInt(in.readLine());
        Student student = studentService.selectStudent(id);
        out.println("=========================================");

        if (student != null) {
            out.println(student);
        }else{
            out.println("Student not found");
        }
        out.println("=========================================");
    }

    void selectAllStudents(PrintWriter out){
        List<Student> studentList = studentService.selectAllStudents();
        out.println("=========================================");
        out.println( studentList.size() + " results found");
        for(Student student: studentList){
            out.println(student);
        }
        out.println("=========================================");
    }

    void deleteStudent(BufferedReader in, PrintWriter out) throws IOException{
        out.println("Student Id:");
        int id = Integer.parseInt(in.readLine());
        boolean deleted = studentService.deleteStudent(id);
        if (deleted){
            out.println("Student deleted successfully");
        }else {
            out.println("Student not found");
        }
    }

    void updateStudent(BufferedReader in, PrintWriter out) throws IOException{
        out.println("Student Id:");
        int id = Integer.parseInt(in.readLine());
        Student student = studentService.selectStudent(id);
        out.println("=========================================");

        if (student != null) {
            out.println(student);
        }else{
            out.println("Student not found");
            return ;
        }

        out.println("What do you want to change?");
        out.println("1. Name");
        out.println("2. Major");
        out.println("3. GPA");

        int userInput = Integer.parseInt(in.readLine());
        switch (userInput){
            case 1:
                out.println("New Name:");
                String name = in.readLine();
                updateName(student, name);
                break;
            case 2:
                out.println("New Major:");
                String major = in.readLine();
                updateMajor(student, major);
                break;
            case 3:
                out.println("New GPA:");
                String gpa = in.readLine();
                updateGPA(student, gpa);
                break;
        }

    }

    void updateName(Student student, String name){
        student.setName(name);
    }

    void updateMajor(Student student, String major){
        student.setMajor(major);
    }

    void updateGPA(Student student, double gpa){
        student.setGpa(gpa);
    }

}