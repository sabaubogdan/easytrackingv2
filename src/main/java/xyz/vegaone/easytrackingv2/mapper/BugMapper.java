package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import xyz.vegaone.easytrackingv2.domain.BugEntity;
import xyz.vegaone.easytrackingv2.domain.UserStoryEntity;
import xyz.vegaone.easytrackingv2.dto.Bug;
import xyz.vegaone.easytrackingv2.dto.UserStory;

import java.util.List;

@Mapper(componentModel = "Spring")
public abstract class BugMapper {

    @Mapping(target = "userStory", ignore = true)
    public abstract Bug domainToDto(BugEntity bugEntity);

    @Mapping(target = "userStory", ignore = true)
    public abstract BugEntity dtoToDomain(Bug bug);

    public abstract List<Bug> domainToDtoList(List<BugEntity> bugEntityList);

    public abstract List<BugEntity> dtoToDomainList(List<Bug> bugList);


    @AfterMapping
    void addIgnoredFieldsToDto(BugEntity bugEntity, @MappingTarget Bug bug) {
        if (bugEntity.getUserStory() != null) {
            UserStory userStory = new UserStory();
            userStory.setId(bugEntity.getUserStory().getId());

            bug.setUserStory(userStory);
        }
    }

    @AfterMapping
    void addIgnoredFieldsToDomain(Bug bug, @MappingTarget BugEntity bugEntity) {
        if (bug.getUserStory() != null) {
            UserStoryEntity userStoryEntity = new UserStoryEntity();
            userStoryEntity.setId(bug.getUserStory().getId());

            bugEntity.setUserStory(userStoryEntity);
        }
    }

}
