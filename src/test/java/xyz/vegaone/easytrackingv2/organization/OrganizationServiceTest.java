package xyz.vegaone.easytrackingv2.organization;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.vegaone.easytrackingv2.dto.Organization;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.service.OrganizationService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrganizationServiceTest {

    private static final String ORGANIZATION_NAME = "DDB Solutions";
    private static final String NEW_ORGANIZATION_NAME = "DDB Solutions2";

    @Autowired
    private OrganizationService organizationService;

    @Test
    public void createOrganizationTest() {
        //when
        Organization savedOrganization = buildAndSaveOrganization();

        //then
        Organization findOrganization = organizationService.getOrganization(savedOrganization.getId(), true);
        Assert.assertNotNull("There should have been one organization saved in the database", findOrganization);
        Assert.assertEquals("The organization name should have matched", ORGANIZATION_NAME, findOrganization.getName());
    }

    @Test
    public void getOrganizationTest() {
        //given
        Organization savedOrganization = buildAndSaveOrganization();

        //when
        Organization findOrganization = organizationService.getOrganization(savedOrganization.getId(), true);

        //then
        Assert.assertNotNull("There should have been one organization saved in the database", findOrganization);
        Assert.assertEquals("The organization name should have matched", savedOrganization.getName(), findOrganization.getName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteOrganizationTest() {
        //given
        Organization savedOrganization = buildAndSaveOrganization();

        //when
        organizationService.deleteOrganization(savedOrganization.getId());

        //then
        Assert.assertNull("The organization should have been deleted", organizationService.getOrganization(savedOrganization.getId(), true));

    }

    @Test
    public void updateOrganizationTest() {
        //given
        Organization savedOrganization = buildAndSaveOrganization();

        //when
        savedOrganization.setName(NEW_ORGANIZATION_NAME);
        Organization updateOrganization = organizationService.updateOrganization(savedOrganization);

        //then
        Assert.assertNotNull("There should have been one organization saved in the database", savedOrganization);
        Assert.assertEquals("The new organization name should match", NEW_ORGANIZATION_NAME, updateOrganization.getName());

    }


    @Test(expected = EntityNotFoundException.class)
    public void organizationNotFoundException() throws EntityNotFoundException {
        //given
        Organization savedOrganization = buildAndSaveOrganization();
        organizationService.deleteOrganization(savedOrganization.getId());

        //when
        long nonexistentOrganizationId = -1;
        organizationService.getOrganization(nonexistentOrganizationId, true);
    }


    private Organization buildAndSaveOrganization() {
        Organization organization = new Organization();

        organization.setName(ORGANIZATION_NAME);

        return organizationService.createOrganization(organization);
    }

}
