package xyz.vegaone.easytrackingv2.bug;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytrackingv2.dto.Bug;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.service.BugService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BugServiceTest {

    private static final String BUG_NAME = "Bug name";
    private static final String BUG_NEW_NAME = "Bug name";
    private static final String BUG_DESCRIPTION = "Bug description";
    private static final String BUG_STATUS = "Bug status";
    private static final String BUG_SEVERITY = "Bug severity";
    private static final Integer BUG_PRIORITY = 1;
    private static final Integer BUG_ESTIMATION = 55;

    @Autowired
    private BugService bugService;

    @Test
    public void createBug() {
        //given

        //when
        Bug savedBug = buildAndSaveBug();

        //then
        Assert.assertNotNull("There should have been one bug saved in the database", savedBug);
        Assert.assertEquals("The bug name should have matched", BUG_NAME, savedBug.getName());
        Assert.assertEquals("The bug description should have matched", BUG_DESCRIPTION, savedBug.getDescription());
        Assert.assertEquals("The bug status should have matched", BUG_STATUS, savedBug.getStatus());
        Assert.assertEquals("The bug priority should have matched", BUG_PRIORITY, savedBug.getPriority());
        Assert.assertEquals("The bug estimation should have matched", BUG_ESTIMATION, savedBug.getEstimation());
        Assert.assertEquals("The bug estimation should have matched", BUG_SEVERITY, savedBug.getSeverity());
    }

    @Test
    public void getBug() {
        //given
        Bug savedBug = buildAndSaveBug();

        //when
        Bug findBug = bugService.getBug(savedBug.getId());

        //then
        Assert.assertNotNull("There should have been one bug saved in the database", findBug);
        Assert.assertEquals("The bug name should have matched", BUG_NAME, findBug.getName());
        Assert.assertEquals("The bug description should have matched", BUG_DESCRIPTION, findBug.getDescription());
        Assert.assertEquals("The bug status should have matched", BUG_STATUS, findBug.getStatus());
        Assert.assertEquals("The bug priority should have matched", BUG_PRIORITY, findBug.getPriority());
        Assert.assertEquals("The bug estimation should have matched", BUG_ESTIMATION, findBug.getEstimation());
        Assert.assertEquals("The bug estimation should have matched", BUG_SEVERITY, findBug.getSeverity());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteBug() {
        //given
        Bug savedBug = buildAndSaveBug();

        //when
        bugService.deleteBug(savedBug.getId());

        //then
        Assert.assertNull("The bug should have been deleted", bugService.getBug(savedBug.getId()));

    }

    @Test
    public void updateBug() {
        //given
        Bug savedBug = buildAndSaveBug();

        //when
        savedBug.setName(BUG_NEW_NAME);
        Bug updatedBug = bugService.updateBug(savedBug);

        //then
        Assert.assertNotNull("There should have been one task saved in the database", savedBug);
        Assert.assertEquals("The new email should match", BUG_NEW_NAME, updatedBug.getName());
    }


    public Bug buildAndSaveBug() {
        Bug bug = new Bug();
        bug.setDescription(BUG_DESCRIPTION);
        bug.setEstimation(BUG_ESTIMATION);
        bug.setName(BUG_NAME);
        bug.setStatus(BUG_STATUS);
        bug.setSeverity(BUG_SEVERITY);
        bug.setPriority(BUG_PRIORITY);

        Bug savedBug = bugService.createBug(bug);

        return savedBug;

    }

    @Test(expected = EntityNotFoundException.class)
    public void bugNotFoundExceptionTest() throws EntityNotFoundException {
        //given
        Bug savedBug = buildAndSaveBug();
        bugService.deleteBug(savedBug.getId());
        //when
        long nonexistentBug = -1;
        bugService.getBug(nonexistentBug);
    }


}
