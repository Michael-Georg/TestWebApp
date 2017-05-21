package com.example.mappers;

import com.example.controllers.UsersController;
import com.example.entities.UserEntity;
import com.example.entities.UserWithProducts;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UsersController usersController;

    @Before
    public void before() {
        userMapper.create(getUser());
    }

    @After
    public void after() {
        UserEntity byEmail = userMapper.getByEmail(getUser().getEmail());
        if (byEmail != null)
            userMapper.delete(byEmail.getId());
    }

    @Test
    public void getByEmail() throws Exception {
        UserWithProducts entity = userMapper.getByEmail(getUser().getEmail());
        assertThat(entity.getName(), is(getUser().getName()));
        assertThat(entity.getSurname(), is(getUser().getSurname()));
    }

    @Test
    public void delete() throws Exception {
        UserWithProducts byEmail = userMapper.getByEmail(getUser().getEmail());
        assertTrue(userMapper.delete(byEmail.getId()));
        assertEquals(userMapper.getByEmail(byEmail.getEmail()), null);
    }

    @Test
    public void update() {
        String newName = "newName";
        UserEntity userEntity = userMapper.getByEmail(getUser().getEmail()).setName(newName);
        final Boolean result = userMapper.update(userEntity);
        String actual = userMapper.getByEmail(getUser().getEmail()).getName();
        assertThat(actual, is(newName));
        assertThat(result, is(true));
    }

    @Test
    public void getAll() throws Exception {
        Optional<UserEntity> any = userMapper.getAll().stream()
                .filter(entity -> entity.getEmail().equals(getUser().getEmail()))
                .findAny();
        assertTrue(any.isPresent());
        assertTrue(any.get().getId() != null);
    }

    UserEntity getUser() {
        UserEntity user = new UserEntity();
        user.setName("Darth")
                .setSurname("Vader")
                .setEmail("DarthVader@mail.com");
        return user;
    }
}