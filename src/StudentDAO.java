import java.util.List;

public interface StudentDAO {

    // Create student
    void insertStudent(Student student);

    // Update student
    boolean updateStudent(Student student);

    // Select student by id
    Student selectStudent(int id);

    // Select students
    List<Student> selectAllStudents();

    // Delete student
    boolean deleteStudent(int id);

}
