package ku.cs.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class StudentTest {
    private StudentList list;

    @BeforeEach
    void initial() {
        list = new StudentList();
        list.addNewStudent("600000001", "Imm");
    }

    @Test
    @DisplayName("ทดสอบการเพิ่มคะแนน 45.15 คะแนน")
    void testAddScore(){
        Student s = new Student("6xxxxxxxx", "StudentTest");
        s.addScore(45.15);
        assertEquals(45.15, s.getScore());

    }

    @Test
    @DisplayName("ทดสอบการเพิ่มคะแนน 85 คะแนน และให้ Object คำนวนเกรดออกมา")
    void testCalculateGrade(){
        Student s = new Student("6xxxxxxxxx", "StudentTest");
        s.addScore(85);
        assertEquals("A", s.grade());
    }

    @Test
    @DisplayName("ทดสอบการเปลี่ยนชื่อ")
    void testChangeName() {
        Student s = new Student("6xxxxxxxxx", "StudentTest");
        s.changeName("Mickey");
        assertEquals("Mickey", s.getName());
    }

    @Test
    @DisplayName("ทดสอบการค้นหานักเรียนด้วยรหัสนิสิต")
    void testFindNewStudentById() {
        Student s1 = list.findStudentById("600000001");
        assertEquals("600000001", s1.getId());
    }


    @Test
    @DisplayName("ทดสอบการหานักเรียนด้วยชื่อ")
    void testFilterByName() {
        list.addNewStudent("600000002", "Jane");
        list.addNewStudent("600000003", "Jimmy");
        list.addNewStudent("600000004", "Janeeyeh");

        StudentList filteredList1 = list.filterByName("J");
        assertEquals(3, filteredList1.getStudents().size());

        StudentList filteredList2 = list.filterByName("Jane");
        assertEquals(2, filteredList2.getStudents().size());

        StudentList filteredList4 = list.filterByName("Peter");
        assertEquals(0, filteredList4.getStudents().size());
    }

    @Test
    @DisplayName("ทดสอบการให้คะแนนด้วยรหัสนิสิต")
    void testGiveScoreToId() {
        list.addNewStudent("600000002", "Jane");

        Student jane = list.findStudentById("600000002");

        list.giveScoreToId("600000002", 50.5);
        assertEquals(50.5, jane.getScore());

        list.giveScoreToId("600000002", 10.0);
        assertEquals(60.5, jane.getScore());
    }

    @Test
    @DisplayName("ทดสอบการดูเกรดด้วยรหัสนิสิต")
    void testViewGradeOfId() {
        list.addNewStudent("600000002", "Jane", 79.0);

        assertEquals("B", list.viewGradeOfId("600000002"));
    }

}