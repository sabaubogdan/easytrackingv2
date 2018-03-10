package xyz.vegaone.easytrackingv2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;

import java.util.List;

public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {

    List<ProjectEntity> findAllByUserList(List<UserEntity> userList);

    @Query("SELECT new xyz.vegaone.easytrackingv2.domain.ProjóectEntity (id, name)  FROM  project")
    List<ProjectEntity> findAllProjectsAndDisplayProjectIdAndName();
}
