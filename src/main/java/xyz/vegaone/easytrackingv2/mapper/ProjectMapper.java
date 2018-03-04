package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytrackingv2.domain.ProjectEntity;
import xyz.vegaone.easytrackingv2.dto.Project;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface ProjectMapper {

    Project domainToDto(ProjectEntity projectEntity);

    ProjectEntity dtoToDomain(Project project);

    List<Project> domainToDtoList(List<ProjectEntity> projectEntityList);

    List<ProjectEntity> dtoToDomainList(List<Project> projectList);

}
