package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytrackingv2.domain.SprintEntity;
import xyz.vegaone.easytrackingv2.dto.Sprint;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface SprintMapper {

    Sprint domainToDto(SprintEntity sprintEntity);

    SprintEntity dtoToDomain(Sprint sprint);

    List<Sprint> domainToDtoList(List<SprintEntity> sprintEntityList);

    List<SprintEntity> dtoToDomainList(List<Sprint> sprintList);

}
