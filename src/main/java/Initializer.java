import com.nixsolutions.entity.*;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

public class Initializer {

    private final EntityManager entityManager;

    public Initializer(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void initialize() {

        Topic topic = new Topic("Fire Bending");
        entityManager.persist(topic);
        Topic topic1 = new Topic("Air Bending");
        entityManager.persist(topic1);

        Teacher teacher = new Teacher("John Smith");
        entityManager.persist(teacher);
        Teacher teacher1 = new Teacher("Avatar Aang");
        entityManager.persist(teacher1);

        Course course = new Course("first");
        entityManager.persist(course);
        Course course1 = new Course("second");
        entityManager.persist(course1);

        Group group = new Group("401-TK");
        Group group1 = new Group("402-TK");
        group.setCourse(course);
        group1.setCourse(course1);

        Student student = new Student("Vlad Shahno");
        Student student1 = new Student("Fire Lord");
        student.setGroup(group);
        student1.setGroup(group1);
        entityManager.persist(group);
        entityManager.persist(group1);
        entityManager.persist(student);
        entityManager.persist(student1);

        Lesson lesson = new Lesson(LocalDateTime.of(2021, 8, 11,
                13, 10, 0), "Reflection");
        Lesson lesson1 = new Lesson(LocalDateTime.of(2021, 8, 9,
                11, 15, 0), "Earth Bending");
        Lesson lesson2 = new Lesson(LocalDateTime.of(2021, 8, 17,
                13, 10, 0), "Water Bending");
        lesson.setTeacher(teacher);
        lesson.setTopic(topic);
        lesson.setGroup(group);

        lesson1.setTeacher(teacher1);
        lesson1.setTopic(topic1);
        lesson1.setGroup(group);

        lesson2.setTeacher(teacher);
        lesson2.setTopic(topic);
        lesson2.setGroup(group1);

        entityManager.persist(lesson);
        entityManager.persist(lesson1);
        entityManager.persist(lesson2);

        Grade grade = new Grade();
        grade.setLesson(lesson);
        grade.setStudent(student);
        grade.setMark(5);
        entityManager.persist(grade);
    }
}
