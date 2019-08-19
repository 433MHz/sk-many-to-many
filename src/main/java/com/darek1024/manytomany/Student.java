package com.darek1024.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "students")
public class Student {

    @Id
    private Long id;
    private String name;

    @ManyToMany(
        cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
        name = "users_groups",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<Group> groups = new HashSet<>();

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
        group.getStudents().add(this);
    }

    public void removeGroup(Group group) {
        this.groups.remove(group);
        group.getStudents().remove(this);
    }

    public Set<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(final Set<Group> groups) {
        this.groups = groups;
    }
}
