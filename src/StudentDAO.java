import java.io.IOException;
import java.util.List;

public interface StudentDAO {

    // Create student
    void insertStudent(Student student, boolean toFile) throws IOException;

    // Update student
    boolean updateStudent(Student student);

    // Select student by id
    Student selectStudent(int id) throws IOException;

    // Select students
    List<Student> selectAllStudents() throws IOException;

    // Delete student
    void deleteStudent(int id) throws IOException;

}
