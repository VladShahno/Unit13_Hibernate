package com.nixsolutions.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lesson_name",nullable = false)
    private String lessonName;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Lesson() {}

    public Lesson(LocalDateTime date, String lessonName) {
        this.date = date;
        this.lessonName = lessonName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime localDateTime) {
        this.date = localDateTime;
    }

    public String getName() {
        return lessonName;
    }

    public void setName(String name) {
        this.lessonName = name;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
        topic.addLesson(this);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
        group.addLesson(this);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.addLesson(this);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id = " + id +
                ", Lesson = '" + lessonName + '\'' +
                ", Date - " + date +
                '}';
    }
}
