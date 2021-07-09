import java.io.IOException;
import java.util.List;

public class StudentService {
    private static StudentService instance;
    private static StudentDAO studentDAO;

    private StudentService(){
        studentDAO = StudentDAOImpl.getInstance();
    }

    public static StudentService getInstance(){
        if (instance == null){
            instance = new StudentService();
            studentDAO = StudentDAOImpl.getInstance();
        }
        return instance;
    }

    // Create student
    void insertStudent(Student student, boolean toFile) throws IOException {
        studentDAO.insertStudent(student, toFile);
    }

    // Update student
    boolean updateStudent(Student student){
        return studentDAO.updateStudent(student);
    }

    // Select student by id
    Student selectStudent(int id) throws IOException {
        return studentDAO.selectStudent(id);
    }

    // Select students
    List<Student> selectAllStudents() throws IOException {
        return studentDAO.selectAllStudents();
    }

    // Delete student
    void deleteStudent(int id) throws IOException {
        studentDAO.deleteStudent(id);
    }

}
