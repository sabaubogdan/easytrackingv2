package xyz.vegaone.easytrackingv2.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytrackingv2.dto.User;
import xyz.vegaone.easytrackingv2.exception.UserNotFoundException;
import xyz.vegaone.easytrackingv2.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    private static final String NAME = "Bogdan";
    private static final String EMAIL = "email@email.com";
    private static final String NEW_EMAIL = "email2@email.com";

    @Autowired
    private UserService userService;

    @Test
    public void createUserTest() {
        //when
        User savedUser = buildAndSaveUser();

        //then
        User findUser = userService.getUser(savedUser.getId());
        Assert.assertNotNull("There should have been one user saved in the database", savedUser);
        Assert.assertEquals("The user name should have matched", NAME, findUser.getName());
        Assert.assertEquals("The user email should have matched", EMAIL, findUser.getEmail());

    }

    @Test
    public void getUserTest() {
        //given
        User savedUser = buildAndSaveUser();

        //when
        User findUser = userService.getUser(savedUser.getId());

        //then
        Assert.assertNotNull("There should have been one user saved in the database", savedUser);
        Assert.assertEquals("The user name should have matched", savedUser.getName(), findUser.getName());
        Assert.assertEquals("The user email should have matched", savedUser.getEmail(), findUser.getEmail());
    }

    @Test
    public void deleteUserTest() {
        //given
        User savedUser = buildAndSaveUser();

        //when
        userService.deleteUser(savedUser.getId());

        //then
        Assert.assertNull("The user should have been deletaed", userService.getUser(savedUser.getId()));
    }

    @Test
    public void updateUserTest() {
        //given
        User savedUser = buildAndSaveUser();

        //when
        savedUser.setEmail(NEW_EMAIL);
        User updatedUser = userService.updateUser(savedUser);

        //then
        Assert.assertNotNull("There should have been one user saved in the database", savedUser);
        Assert.assertEquals("The new email should match", NEW_EMAIL, updatedUser.getEmail());

    }

    @Test(expected = UserNotFoundException.class)
    public void userNotFoundExceptionTest() throws UserNotFoundException {
        //given
        User savedUser = buildAndSaveUser();
        userService.deleteUser(savedUser.getId());
        //when
        long nonexistentUserId = -1;
        userService.getUser(nonexistentUserId);


    }

    private User buildAndSaveUser() {
        User user = new User();
        user.setEmail(EMAIL);
        user.setName(NAME);

        return userService.createUser(user);
    }
}
