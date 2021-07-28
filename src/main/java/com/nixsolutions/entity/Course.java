package com.nixsolutions.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String course_name;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Group> groups = new ArrayList<>();

    public Course(String name) {
        this.course_name = name;
    }

    public Course() {
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String name) {
        this.course_name = name;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }
}

