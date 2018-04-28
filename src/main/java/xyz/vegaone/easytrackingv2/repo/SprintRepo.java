package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vegaone.easytrackingv2.domain.SprintEntity;

import java.util.List;

public interface SprintRepo extends JpaRepository<SprintEntity, Long> {

    List<SprintEntity> findAllByProjectId(Long projectId);
}
