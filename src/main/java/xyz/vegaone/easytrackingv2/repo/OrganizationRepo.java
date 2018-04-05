package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;

public interface OrganizationRepo extends JpaRepository<OrganizationEntity, Long> {
}
