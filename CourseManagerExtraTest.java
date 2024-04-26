import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CourseManagerExtraTest {
    private CourseManager manager;

    @BeforeEach
    void setUp() {
        this.manager = new CourseManager();
        this.manager.setIfOpen(true);
    }

    @AfterEach
    void tearDown() {
        manager = null;
    }

    @Test
    void extraTest1() {
        Course extraCourse = new Course("CS_Extra", "Extra Course", 3);
        manager.addCourse(extraCourse);
        Course helpCourse = new Course("CS_Help", "Help Course", 3);
        manager.addCourse(helpCourse);
        Student extraStudent1 = new Student("ID_e1", "student_e1@example.com", "Student_e1", 50);
        Student extraStudent2 = new Student("ID_e2", "student_e2@example.com", "Student_e2", 50);
        Student extraStudent3 = new Student("ID_e3", "student_e3@example.com", "Student_e3", 50);
        Student extraStudent4 = new Student("ID_e4", "student_e4@example.com", "Student_e4", 50);
        manager.addStudent(extraStudent1);
        manager.addStudent(extraStudent2);
        manager.addStudent(extraStudent3);
        manager.addStudent(extraStudent4);


        assertTrue(extraStudent1.enrollCourse("CS_Extra", 30));
        assertTrue(extraStudent2.enrollCourse("CS_Extra", 20));
        assertTrue(extraStudent3.enrollCourse("CS_Extra", 20));
        assertFalse(extraStudent4.enrollCourse("CS_Extra", 60));
        assertTrue(extraStudent4.enrollCourse("CS_Extra", 20));
        assertTrue(extraStudent1.modifyEnrollCredit("CS_Extra", 20));
        assertTrue(extraStudent2.modifyEnrollCredit("CS_Extra", 25));
        assertTrue(extraStudent3.dropEnrollCourse("CS_Extra"));
        assertTrue(extraStudent3.enrollCourse("CS_Extra", 30));

        assertEquals(50 - 20, extraStudent1.getCredits());
        assertEquals(50 - 30, extraStudent3.getCredits());
        assertEquals(50 - 20, extraStudent4.getCredits());

        assertFalse(extraStudent4.enrollCourse("CS_Help", 35));
        assertTrue(extraStudent4.enrollCourse("CS_Help", 30));

        List<String> student4Courses = extraStudent4.getCoursesWithScores();
        assertEquals(2, student4Courses.size()); //
        assertTrue(student4Courses.contains("CS_Help: 30"));
        assertTrue(student4Courses.contains("CS_Extra: 20")); //

        manager.finalizeEnrollments();


        assertEquals(2, extraCourse.getSuccessStudents().size());
        //
        assertTrue(extraCourse.getSuccessStudents().contains(extraStudent3));
        assertTrue(extraCourse.getSuccessStudents().contains(extraStudent2));
    }

    @Test
    void extraTest2() {
        Student s1 = new Student("s1", "xxx", "A", 40);
        Student s2 = new Student("s2", "xxx", "A", 40);
        Student s3 = new Student("s3", "xxx", "A", 40);
        Student s4 = new Student("s4", "xxx", "A", 40);
        Course c1 = new Course("c1", "CS111", 3);
        manager.addStudent(s1);
        manager.addStudent(s2);
        manager.addStudent(s3);
        manager.addStudent(s4);
        manager.addCourse(c1);
        assertTrue(s1.enrollCourse("c1", 30));
        assertTrue(s2.enrollCourse("c1", 30));
        assertTrue(s3.enrollCourse("c1", 30));
        assertTrue(s4.enrollCourse("c1", 10));
        assertTrue(s4.dropEnrollCourse("c1"));
        assertFalse(c1.getEnrollStudent().contains(s4));
        manager.finalizeEnrollments();
        assertEquals(3, c1.getSuccessStudents().size());
    }
      @Test
    void extraTest3(){
        Student s1 = new Student("s1", "xxx", "A", 40);
        Student s2 = new Student("s2", "xxx", "A", 40);
        Student s3 = new Student("s3", "xxx", "A", 40);
        Student s4 = new Student("s4", "xxx", "A", 40);
        Student s5 = new Student("s5", "xxx", "A", 40);
        Student s6 = new Student("s6", "xxx", "A", 40);
        Course c1 = new Course("c1", "CS111", 4);
        manager.addStudent(s1);
        manager.addStudent(s2);
        manager.addStudent(s3);
        manager.addStudent(s4);
        manager.addStudent(s5);
        manager.addStudent(s6);
        manager.addCourse(c1);
        assertTrue(s1.enrollCourse("c1", 10));
        assertTrue(s2.enrollCourse("c1", 20));
        assertTrue(s3.enrollCourse("c1", 30));
        assertTrue(s4.enrollCourse("c1", 15));
        assertTrue(s5.enrollCourse("c1", 15));
        assertTrue(s6.enrollCourse("c1", 10));
        assertTrue(s3.modifyEnrollCredit("c1",5));
        manager.finalizeEnrollments();
        assertEquals(3, c1.getSuccessStudents().size());
    }
    @Test
    void extraTest4(){
        Student s1 = new Student("s1", "xxx", "A", 40);
        Student s2 = new Student("s2", "xxx", "A", 40);
        Student s3 = new Student("s3", "xxx", "A", 40);
        Course c1 = new Course("c1", "CS111", 2);
        Course c2 = new Course("c2", "CS222", 2);
        Course c3 = new Course("c3", "CS333", 2);
        manager.addStudent(s1);
        manager.addStudent(s2);
        manager.addStudent(s3);
        manager.addCourse(c1);
        manager.addCourse(c2);
        manager.addCourse(c3);
        s1.enrollCourse("c1",20);
        s1.dropEnrollCourse("c1");
        s2.enrollCourse("c2",20);
        s2.modifyEnrollCredit("c2",15);
        s3.dropEnrollCourse("c3");
        s3.modifyEnrollCredit("c3",10);
        manager.finalizeEnrollments();
    }
}
