package xyz.vegaone.easytrackingv2.userStory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytrackingv2.dto.UserStory;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.service.UserStoryService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserStoryServiceTest {

    private static final String USER_STORY_NAME = "user story name";
    private static final String NEW_USER_STORY_NAME = "New user story name";
    private static final String USER_STORY_DESCRIPTION = "user story name";
    private static final String USER_STORY_STATUS = "user story name";
    private static final Long USER_STORY_PRIORITY = 1L;
    private static final Long USER_STORY_ESTIMATION = 2L;

    @Autowired
    private UserStoryService userStoryService;

    @Test
    public void createUserStoryTest() {
        //given

        //when
        UserStory savedUserStory = buildAndSaveUserStory();

        //then
        Assert.assertNotNull("There should have been one user story saved in the database", savedUserStory);
        Assert.assertEquals("The user story name should have matched", USER_STORY_NAME, savedUserStory.getName());
        Assert.assertEquals("The user story description should have matched", USER_STORY_DESCRIPTION, savedUserStory.getDescription());
        Assert.assertEquals("The user story status should have matched", USER_STORY_STATUS, savedUserStory.getStatus());
        Assert.assertEquals("The user story priority should have matched", USER_STORY_PRIORITY, savedUserStory.getPriority());
        Assert.assertEquals("The user story estimation should have matched", USER_STORY_ESTIMATION, savedUserStory.getEstimation());


    }

    @Test
    public void getUserStory() {
        //given
        UserStory savedUserStory = buildAndSaveUserStory();

        //when
        UserStory findUserStory = userStoryService.getUserStory(savedUserStory.getId());

        //then
        Assert.assertNotNull("There should have been one user story saved in the database", savedUserStory);
        Assert.assertEquals("The user story name should have matched", findUserStory.getName(), savedUserStory.getName());
        Assert.assertEquals("The user story description should have matched", findUserStory.getDescription(), savedUserStory.getDescription());
        Assert.assertEquals("The user story status should have matched", findUserStory.getStatus(), savedUserStory.getStatus());
        Assert.assertEquals("The user story priority should have matched", findUserStory.getPriority(), savedUserStory.getPriority());
        Assert.assertEquals("The user story estimation should have matched", findUserStory.getEstimation(), savedUserStory.getEstimation());

    }

    @Test
    public void deleteUserStoryTest() {
        //given
        UserStory savedUserStory = buildAndSaveUserStory();

        //when
        userStoryService.deleteUserStory(savedUserStory.getId());

        //then
        Assert.assertNull("The user story should have been deleted", userStoryService.getUserStory(savedUserStory.getId()));

    }

    @Test
    public void updateUserStoryTest() {
        //given
        UserStory savedUserStory = buildAndSaveUserStory();

        //when
        savedUserStory.setName(NEW_USER_STORY_NAME);
        UserStory updatedUserStory = userStoryService.updateUserStory(savedUserStory);

        //then
        Assert.assertNotNull("There should have been one user story saved in the database", savedUserStory);
        Assert.assertEquals("The new email should match", NEW_USER_STORY_NAME, updatedUserStory.getName());
    }


    private UserStory buildAndSaveUserStory() {
        UserStory userStory = new UserStory();
        userStory.setDescription(USER_STORY_DESCRIPTION);
        userStory.setEstimation(USER_STORY_ESTIMATION);
        userStory.setName(USER_STORY_NAME);
        userStory.setStatus(USER_STORY_STATUS);
        userStory.setPriority(USER_STORY_PRIORITY);

        return userStoryService.createUserStory(userStory);
    }

    @Test(expected = EntityNotFoundException.class)
    public void userStoryNotFoundExceptionTest() throws EntityNotFoundException {
        //given
        UserStory savedUserStory = buildAndSaveUserStory();
        userStoryService.deleteUserStory(savedUserStory.getId());
        //when
        long nonexistentUserId = -1;
        userStoryService.getUserStory(nonexistentUserId);
    }
}
