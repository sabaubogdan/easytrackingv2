package xyz.vegaone.easytrackingv2.sprint;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytrackingv2.dto.Sprint;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.service.SprintService;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SprintServiceTest {

    private static final Date SPRINT_START_DATE = new Date(2018, 03, 04);
    private static final Date SPRINT_END_DATE = new Date(2018, 03, 11);
    private static final Date NEW_SPRINT_END_DATE = new Date(2018, 03, 12);
    private static final Long SPRINT_NUMBER = 1L;

    @Autowired
    private SprintService sprintService;

    @Test
    public void createSprintTest() {
        //given
        Sprint savedSprint = buildAndSaveSprint();

        //when
        Sprint foundSprint = sprintService.getSprint(savedSprint.getId());

        //then
        Assert.assertNotNull("There should have been one sprint saved in the database", foundSprint);
        Assert.assertEquals("The sprint start date should have matched", SPRINT_START_DATE, foundSprint.getStartDate());
        Assert.assertEquals("The sprint end date should have matched", SPRINT_END_DATE, foundSprint.getEndDate());
        Assert.assertEquals("The sprint sprint number should have matched", SPRINT_NUMBER, foundSprint.getSprintNumber());

    }

    @Test
    public void getSprintTest() {
        //given
        Sprint savedSprint = buildAndSaveSprint();

        //when
        Sprint foundSprint = sprintService.getSprint(savedSprint.getId());

        //then
        Assert.assertNotNull("There should have been one sprint saved in the database", foundSprint);
        Assert.assertEquals("The sprint start date should have matched", savedSprint.getStartDate(), foundSprint.getStartDate());
        Assert.assertEquals("The sprint start date should have matched", savedSprint.getEndDate(), foundSprint.getEndDate());
        Assert.assertEquals("The sprint start date should have matched", savedSprint.getSprintNumber(), foundSprint.getSprintNumber());

    }

    @Test
    public void deleteSprintTest() {
        //given
        Sprint savedSprint = buildAndSaveSprint();

        //when
        sprintService.deletSprint(savedSprint.getId());

        //then
        Assert.assertNull("The sprint should have been deleted", sprintService.getSprint(savedSprint.getId()));

    }

    @Test
    public void updateSrintTest() {
        //given
        Sprint savedSprint = buildAndSaveSprint();

        //when
        savedSprint.setEndDate(NEW_SPRINT_END_DATE);

        //then
        Assert.assertNotNull("There should have been one sprint saved in the database", savedSprint);
        Assert.assertEquals("The new end date should match", NEW_SPRINT_END_DATE, savedSprint.getEndDate());

    }


    public Sprint buildAndSaveSprint() {
        Sprint sprint = new Sprint();
        sprint.setEndDate(SPRINT_END_DATE);
        sprint.setStartDate(SPRINT_START_DATE);
        sprint.setSprintNumber(SPRINT_NUMBER);

        return sprintService.createSprint(sprint);
    }

    @Test(expected = EntityNotFoundException.class)
    public void sprintNotFoundExceptionTest() throws EntityNotFoundException {
        //given
        Sprint savedSprint = buildAndSaveSprint();
        sprintService.deletSprint(savedSprint.getId());
        //when
        long nonexistentSprintId = -1;
        sprintService.getSprint(nonexistentSprintId);


    }
}
