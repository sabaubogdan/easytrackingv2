package xyz.vegaone.easytrackingv2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.Organization;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.mapper.OrganizationMapper;
import xyz.vegaone.easytrackingv2.repo.OrganizationRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    private OrganizationRepo organizationRepo;

    private OrganizationMapper organizationMapper;

    @Autowired
    public OrganizationService(OrganizationRepo organizationRepo,
                               OrganizationMapper organizationMapper) {
        this.organizationRepo = organizationRepo;
        this.organizationMapper = organizationMapper;
    }

    public Organization createOrganization(Organization organization) {
        OrganizationEntity organizationEntity = organizationMapper.dtoToDomain(organization);
        OrganizationEntity savedOrganizationEntity = organizationRepo.save(organizationEntity);

        return organizationMapper.domainToDto(savedOrganizationEntity);
    }

    @Transactional
    public Organization getOrganization(Long id) {
        Optional<OrganizationEntity> organizationOptional = organizationRepo.findById(id);

        OrganizationEntity organizationEntity = organizationOptional.orElseThrow(() ->
                new EntityNotFoundException("Organization with id " + id + " not found"));

        return organizationMapper.domainToDto(organizationEntity);
    }

    public void deleteOrganization(Long id) {
        organizationRepo.deleteById(id);
    }

    public Organization updateOrganization(Organization organization) {

        OrganizationEntity organizationEntity = organizationMapper.dtoToDomain(organization);

        OrganizationEntity savedOrganizationEntity = organizationRepo.save(organizationEntity);

        return organizationMapper.domainToDto(savedOrganizationEntity);

    }

    @Transactional
    public List<Organization> findAllOrganizationsBrief() {
        List<OrganizationEntity> organizationEntityList = organizationRepo.findAllOrganizationsBrief();

        return organizationMapper.domainToDtoList(organizationEntityList);
    }

    public List<Organization> findAllOrganizationsByUserId(Long userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        List<OrganizationEntity> organizationEntityList = organizationRepo.findAllByUserList(Collections.singletonList(userEntity));

        return organizationMapper.domainToDtoList(organizationEntityList);
    }
}
