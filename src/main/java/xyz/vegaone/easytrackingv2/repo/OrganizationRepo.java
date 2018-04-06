package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;

import java.util.List;

public interface OrganizationRepo extends JpaRepository<OrganizationEntity, Long> {

    @Query(value = "SELECT new xyz.vegaone.easytrackingv2.domain.OrganizationEntity(name, id) FROM OrganizationEntity")
    List<OrganizationEntity> findAllOrganizations();
}
