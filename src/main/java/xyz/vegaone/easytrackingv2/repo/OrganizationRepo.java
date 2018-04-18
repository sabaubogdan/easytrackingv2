package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepo extends JpaRepository<OrganizationEntity, Long> {

    @Query(value = "SELECT new xyz.vegaone.easytrackingv2.domain.OrganizationEntity(name, id) FROM OrganizationEntity oe " +
            "where oe.id = :organizationId")
    Optional<OrganizationEntity> findByOrganizationId(@Param(value = "organizationId") Long organizationId);

    @Query(value = "SELECT new xyz.vegaone.easytrackingv2.domain.OrganizationEntity(name, id) FROM OrganizationEntity")
    List<OrganizationEntity> findAllOrganizationsBrief();

    List<OrganizationEntity> findAllByUserListIsContaining(UserEntity user);

    @Query(value = "SELECT new xyz.vegaone.easytrackingv2.domain.OrganizationEntity(name, id) FROM OrganizationEntity oe " +
            "where :user member of oe.userList ")
    List<OrganizationEntity> findAllByUserBrief(@Param(value = "user") UserEntity user);
}
