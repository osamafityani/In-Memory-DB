import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    Cache<Integer, Student> cache = Cache.getInstance(5);

    @Override
    public void insertStudent(Student student) {
        cache.put(student.getId(), student);
    }

    @Override
    public boolean updateStudent(Student student) {
        return cache.put(student.getId(), student) != null;

    }

    @Override
    public Student selectStudent(int id) {
        return cache.get(id);
    }

    @Override
    public List<Student> selectAllStudents() {
        List<Student> studentsList = new ArrayList<>();
        studentsList.addAll(cache.values());
        return studentsList;
    }

    @Override
    public boolean deleteStudent(int id) {
        return cache.remove(id) != null;
    }
}
