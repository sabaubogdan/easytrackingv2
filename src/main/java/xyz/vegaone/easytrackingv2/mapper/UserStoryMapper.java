package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.domain.UserStoryEntity;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.dto.UserStory;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserStoryMapper {

    @Mapping(target = "project", ignore = true)
    UserStory domainToDto(UserStoryEntity userStoryEntity);

    @Mapping(target = "project", ignore = true)
    UserStoryEntity dtoToDomain(UserStory userStory);

    List<UserStory> domainToDtoList(List<UserStoryEntity> userStoryEntityList);

    List<UserStoryEntity> dtoToDomain(List<UserStory> userStoryList);

    @AfterMapping
    default void addProjectToDto(UserStoryEntity userStoryEntity, @MappingTarget UserStory userStory) {
        if (userStoryEntity.getProject() != null) {
            Project project = new Project();
            project.setId(userStoryEntity.getProject().getId());
            project.setName(userStoryEntity.getProject().getName());

            userStory.setProject(project);
        }
    }

    @AfterMapping
    default void addProjectToDomain(UserStory userStory, @MappingTarget UserStoryEntity userStoryEntity) {
        if (userStory.getProject() != null) {
            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setId(userStory.getProject().getId());
            projectEntity.setName(userStory.getProject().getName());

            userStoryEntity.setProject(projectEntity);
        }
    }
}
