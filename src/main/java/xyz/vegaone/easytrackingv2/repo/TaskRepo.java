package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vegaone.easytrackingv2.domain.TaskEntity;

import java.time.LocalDate;

public interface TaskRepo extends JpaRepository<TaskEntity, Long> {

    Long countByUserId(Long id);
}
