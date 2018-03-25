package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytrackingv2.domain.BugEntity;
import xyz.vegaone.easytrackingv2.dto.Bug;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface BugMapper {

    Bug domainToDto(BugEntity bugEntity);

    BugEntity dtoToDomain(Bug bug);

    List<Bug> domainToDtoList(List<BugEntity> bugEntityList);

    List<BugEntity> dtoToDomainList(List<Bug> bugList);

}
