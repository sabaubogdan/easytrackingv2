package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vegaone.easytrackingv2.domain.UserStoryEntity;

public interface UserStoryRepo extends JpaRepository<UserStoryEntity, Long> {
}
