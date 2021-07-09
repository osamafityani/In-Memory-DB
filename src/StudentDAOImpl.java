import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    Cache<Integer, Student> cache = Cache.getInstance(2);
    FileService fileService = new FileService();
    String fileName = "students.dat";
    String delimiter = "///!///";

    @Override
    public void insertStudent(Student student, boolean toFile) throws IOException {
        if (toFile) {
            fileService.writeStudent(fileName, student, student.getId());
        } else {
            if ((cache.size() == cache.getSize())) {
                Student studentToBeRemoved = cache.entrySet().iterator().next().getValue();
                fileService.writeStudent(fileName, studentToBeRemoved, studentToBeRemoved.getId());
            }
            cache.put(student.getId(), student);
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        return cache.put(student.getId(), student) != null;
    }

    @Override
    public Student selectStudent(int id) throws IOException {
        if (cache.containsKey(id)){
            return cache.get(id);
        }else{
            Student student = fileService.readStudent(fileName, id, delimiter);
            if (student == null){
                return null;
            }
            if (student.getId() == id){
                cache.put(student.getId(), student);
                return student;
            }else{
                return null;
            }
        }
    }

    @Override
    public List<Student> selectAllStudents() throws IOException {
        List<Student> studentsList = new ArrayList<>();

        studentsList.addAll(cache.values());

        int numLines = fileService.numLines(fileName);
        for (int id = 1; id <= numLines; id++){
            if (!cache.containsKey(id)) {
                Student student = fileService.readStudent(fileName, id, delimiter);
                if (student != null) {
                    studentsList.add(student);
                }
            }
        }
        return studentsList;
    }

    @Override
    public void deleteStudent(int id) throws IOException {
        if (cache.containsKey(id)) {
            cache.remove(id);
        }
        Student student = fileService.readStudent(fileName, id, delimiter);
        if (student != null) {
            if (student.getId() == id) {
                fileService.deleteStudent(fileName, id);
            }
        }


    }
}
