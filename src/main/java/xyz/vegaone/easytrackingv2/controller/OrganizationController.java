package xyz.vegaone.easytrackingv2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv2.dto.Organization;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.service.OrganizationService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/organization")
@Slf4j
public class OrganizationController {

    private OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping(value = "/{id}")
    public Organization getOrganization(@PathVariable(value = "id") Long id) {
        return organizationService.getOrganization(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Organization createOrganization(@RequestBody Organization organization) {
        return organizationService.createOrganization(organization);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Organization updateOrganization(@RequestBody Organization organization) {
        return organizationService.updateOrganization(organization);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable(value = "id") Long id) {
        organizationService.deleteOrganization(id);
    }

    @GetMapping(value = "/all")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Organization> findAllOrganizationsBrief() {
        List<Organization> allOrganizations = organizationService.findAllOrganizationsBrief();

        return allOrganizations;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String organizationNotFoundError(EntityNotFoundException entityNotFoundException) {
        return entityNotFoundException.getErrMsg();
    }
}
