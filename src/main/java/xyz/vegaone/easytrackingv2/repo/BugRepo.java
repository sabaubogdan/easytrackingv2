package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vegaone.easytrackingv2.domain.BugEntity;

public interface BugRepo extends JpaRepository<BugEntity, Long> {
}
