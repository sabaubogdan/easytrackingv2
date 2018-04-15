package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.domain.SprintEntity;
import xyz.vegaone.easytrackingv2.dto.Project;
import xyz.vegaone.easytrackingv2.dto.Sprint;

import java.util.List;

@Mapper(componentModel = "Spring")
public abstract class SprintMapper {

    @Autowired
    private ProjectMapper projectMapper;

    @Mapping(target = "project", ignore = true)
    public abstract Sprint domainToDto(SprintEntity sprintEntity);

    @Mapping(target = "project", ignore = true)
    public abstract SprintEntity dtoToDomain(Sprint sprint);

    public abstract List<Sprint> domainToDtoList(List<SprintEntity> sprintEntityList);

    public abstract List<SprintEntity> dtoToDomainList(List<Sprint> sprintList);

    @AfterMapping
    void addIgnoredFieldsToDto(SprintEntity sprintEntity, @MappingTarget Sprint sprint) {
        // project
        if (sprintEntity.getProject() != null) {
            Project project = new Project();
            project.setId(sprintEntity.getProject().getId());
            project.setTitle(sprintEntity.getProject().getTitle());

            sprint.setProject(project);
        }
    }

    @AfterMapping
    void addIgnoredFieldsToDomain(Sprint sprint, @MappingTarget SprintEntity sprintEntity) {
        // project
        if (sprint.getProject() != null) {
            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setId(sprint.getProject().getId());
            projectEntity.setTitle(sprint.getProject().getTitle());

            sprintEntity.setProject(projectEntity);
        }
    }
}
