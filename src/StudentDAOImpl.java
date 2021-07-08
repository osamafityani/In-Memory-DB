import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    Cache<Integer, Student> cache = Cache.getInstance(5);

    @Override
    public void insertStudent(Student student) {
        cache.put(Student.objectsCounter, student);
    }

    @Override
    public boolean updateStudent(Student student) {
        return false;
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
        if (cache.remove(id) != null){
            return true;
        }else{
            return false;
        }
    }
}
