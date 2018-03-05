package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vegaone.easytrackingv2.domain.SprintEntity;

public interface SprintRepo extends JpaRepository<SprintEntity, Long> {
}
