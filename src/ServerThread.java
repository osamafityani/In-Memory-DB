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
                        selectAllStudents(out);
                        break;
                    case 3:
                        System.out.println("Update");
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

    void selectStudent(BufferedReader in, PrintWriter out) throws IOException{
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

}