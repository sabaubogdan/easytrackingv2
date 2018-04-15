package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vegaone.easytrackingv2.domain.UserStoryEntity;

import java.util.List;

public interface UserStoryRepo extends JpaRepository<UserStoryEntity, Long> {

    List<UserStoryEntity> findAllByProjectId(Long projectId);

    Long countByUserId(Long id);
}
