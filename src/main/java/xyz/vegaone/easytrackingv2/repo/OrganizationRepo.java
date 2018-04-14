package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;

import java.util.List;

public interface OrganizationRepo extends JpaRepository<OrganizationEntity, Long> {

    @Query(value = "SELECT new xyz.vegaone.easytrackingv2.domain.OrganizationEntity(name, id) FROM OrganizationEntity")
    List<OrganizationEntity> findAllOrganizationsBrief();

    List<OrganizationEntity> findAllByUserList(List<UserEntity> userList);
}
