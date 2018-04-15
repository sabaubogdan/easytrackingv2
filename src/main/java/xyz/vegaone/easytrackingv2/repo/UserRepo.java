package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytrackingv2.domain.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

}
