import java.util.List;

public class StudentService {

    private StudentDAO studentDAO;

    public StudentService(){
        studentDAO = new StudentDAOImpl();
    }

    // Create student
    void insertStudent(Student student){
        studentDAO.insertStudent(student);
    }

    // Update student
    boolean updateStudent(Student student){
        return studentDAO.updateStudent(student);
    }

    // Select student by id
    Student selectStudent(int id){
        return studentDAO.selectStudent(id);
    }

    // Select students
    List<Student> selectAllStudents(){
        return studentDAO.selectAllStudents();
    }

    // Delete student
    boolean deleteStudent(int id){
        return studentDAO.deleteStudent(id);
    }

}
