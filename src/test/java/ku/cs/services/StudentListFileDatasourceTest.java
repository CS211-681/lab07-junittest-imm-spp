package ku.cs.services;

import ku.cs.models.Student;
import ku.cs.models.StudentList;
import org.junit.jupiter.api.*;

import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class StudentListFileDatasourceTest {
    private static final String DIRECTORY_NAME = "testdata";
    private static final String FILE_NAME = "students.csv";
    private StudentListFileDatasource datasource;
    private StudentList studentList;

    @BeforeEach
    void setup() {
        datasource = new StudentListFileDatasource(DIRECTORY_NAME, FILE_NAME);
        studentList = new StudentList();

        File file = new File(DIRECTORY_NAME + File.separator + FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterAll
    static void cleanup() {
        File file = new File(DIRECTORY_NAME + File.separator + FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
        File dir = new File(DIRECTORY_NAME);
        if (dir.exists()) {
            dir.delete();
        }
    }

    @Test
    void testWriteAndReadData() {
        studentList.addNewStudent("600000001", "Imm", 85.5);
        studentList.addNewStudent("600000002", "Jane", 72.0);

        datasource.writeData(studentList);

        StudentList readList = datasource.readData();

        assertEquals(2, readList.getStudents().size());

        Student student1 = readList.findStudentById("600000001");

        assertEquals("Imm", student1.getName());
        assertEquals(85.5, student1.getScore());

        Student student2 = readList.findStudentById("600000002");

        assertEquals("Jane", student2.getName());
        assertEquals(72.0, student2.getScore());
    }
}