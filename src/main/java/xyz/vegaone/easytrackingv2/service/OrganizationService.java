package xyz.vegaone.easytrackingv2.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.Organization;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.repo.OrganizationRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    private OrganizationRepo organizationRepo;

    private Mapper mapper;

    @Autowired
    public OrganizationService(OrganizationRepo organizationRepo,
                               Mapper mapper) {
        this.organizationRepo = organizationRepo;
        this.mapper = mapper;
    }

    public Organization createOrganization(Organization organization) {
        OrganizationEntity organizationEntity = mapper.map(organization, OrganizationEntity.class);
        OrganizationEntity savedOrganizationEntity = organizationRepo.save(organizationEntity);

        return mapper.map(savedOrganizationEntity, Organization.class);
    }

    public Organization getOrganization(Long id, Boolean brief) {
        Optional<OrganizationEntity> organizationOptional;

        if (brief) {
            organizationOptional = organizationRepo.findByOrganizationId(id);
        } else {
            organizationOptional = organizationRepo.findById(id);
        }

        OrganizationEntity organizationEntity = organizationOptional.orElseThrow(() ->
                new EntityNotFoundException("Organization with id " + id + " not found"));

        return mapper.map(organizationEntity, Organization.class);
    }

    public void deleteOrganization(Long id) {
        organizationRepo.deleteById(id);
    }

    public Organization updateOrganization(Organization organization) {

        OrganizationEntity organizationEntity = mapper.map(organization, OrganizationEntity.class);

        OrganizationEntity savedOrganizationEntity = organizationRepo.save(organizationEntity);

        return mapper.map(savedOrganizationEntity, Organization.class);

    }

    public List<Organization> findAllOrganizationsBrief(Boolean brief) {
        List<OrganizationEntity> organizationEntityList = new ArrayList<>();
        if (brief) {
            organizationEntityList = organizationRepo.findAllOrganizationsBrief();
        } else {
            organizationEntityList = organizationRepo.findAllOrganizationsBrief(); // TBD later
        }

        List<Organization> organizationList = new ArrayList<>(organizationEntityList.size());

        organizationEntityList.forEach(
                organizationEntity -> organizationList.add(mapper.map(organizationEntity, Organization.class)));

        return organizationList;
    }

    public List<Organization> findAllOrganizationsByUserId(Long userId, Boolean brief) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        List<OrganizationEntity> organizationEntityList = new ArrayList<>();
        if (brief) {
            organizationEntityList = organizationRepo.findAllByUserBrief(userEntity);
        } else {
            organizationEntityList = organizationRepo.findAllByUserListIsContaining(userEntity);
        }

        List<Organization> organizationList = new ArrayList<>(organizationEntityList.size());

        organizationEntityList.forEach(
                organizationEntity -> organizationList.add(mapper.map(organizationEntity, Organization.class)));

        return organizationList;
    }
}
