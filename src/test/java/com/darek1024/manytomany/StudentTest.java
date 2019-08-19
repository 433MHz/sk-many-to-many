package com.darek1024.manytomany;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ManyToManyApplication.class)
@Transactional
public class StudentTest {

    @Resource
    StudentsRepository studentsRepository;

    @Resource
    GroupsRepository groupsRepository;

    @Test
    public void shouldSaveUserToDb() {
        Student student = new Student(1L, "Mariusz");
        Group group = new Group(1L, "Spring Polska");
        student.addGroup(group);

        studentsRepository.save(student);
        Student fetched = studentsRepository.getOne(1L);
        assertEquals("Mariusz", fetched.getName());
        assertEquals(1, fetched.getGroups().size());

        Group fetchedGroup = groupsRepository.getOne(1L);
        assertEquals("Spring Polska", fetchedGroup.getName());
        assertEquals(1, fetchedGroup.getStudents().size());
    }

}
