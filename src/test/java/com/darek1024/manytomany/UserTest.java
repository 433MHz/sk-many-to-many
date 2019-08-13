package com.darek1024.manytomany;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = ManytomanyApplication.class
)
@Transactional
public class UserTest {

    @Resource
    UsersRepository usersRepository;

    @Test
    public void shouldSaveUserToDb() {
        User user = new User(1L, "Mariusz", LocalDateTime.now());
        Group group = new Group(1L, "Spring Polska", LocalDateTime.now());
        Set<Group> groups = new HashSet<>();
        groups.add(group);
        user.setGroups(groups);

        usersRepository.save(user);

        User fetched = usersRepository.getOne(1L);
        assertEquals("Mariusz", fetched.getUsername());
        assertEquals(1, fetched.getGroups().size());
    }

}
