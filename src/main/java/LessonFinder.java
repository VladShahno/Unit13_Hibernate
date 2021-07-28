import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.nixsolutions.entity.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;

public class LessonFinder {

    public void find(){
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            EntityManager entityManager = sessionFactory.createEntityManager();
            try {
                entityManager.getTransaction().begin();
                Initializer initializer = new Initializer(entityManager);
                initializer.initialize();
                Query query = entityManager.createQuery("Select lesson From Lesson lesson " +
                        "join Group groups ON lesson.group.id = groups.id " +
                        "join Student student on student.group.id = groups.id where student.id " +
                        "= 1L order by lesson.date");
                query.setMaxResults(1);
                entityManager.getTransaction().commit();

                Lesson querySingleResult = (Lesson) query.getSingleResult();
                LocalDateTime dateTime = querySingleResult.getDate();

                System.out.println("\nDate: " + dateTime.getYear() + " " +
                        dateTime.getMonth() + " " + dateTime.getDayOfMonth() +
                        "\nTime: " + dateTime.getHour() + ":" + dateTime.getMinute()
                        + "\nLesson: " + querySingleResult.getName() + "\nTopic: " + querySingleResult.getTopic().getTopicName() + "\nTeacher: "
                        + querySingleResult.getTeacher().getFullName());

            } catch (Exception e) {
               entityManager.getTransaction().rollback();
                System.out.println(e.getMessage());
            }
        }
    }
}
