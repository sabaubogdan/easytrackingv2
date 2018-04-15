package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import xyz.vegaone.easytrackingv2.domain.BugEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.domain.UserStoryEntity;
import xyz.vegaone.easytrackingv2.dto.Bug;
import xyz.vegaone.easytrackingv2.dto.User;
import xyz.vegaone.easytrackingv2.dto.UserStory;

import java.util.List;

@Mapper(componentModel = "Spring")
public abstract class BugMapper {

    @Mappings({
            @Mapping(target = "userStory", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    public abstract Bug domainToDto(BugEntity bugEntity);

    @Mappings({
            @Mapping(target = "userStory", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    public abstract BugEntity dtoToDomain(Bug bug);

    public abstract List<Bug> domainToDtoList(List<BugEntity> bugEntityList);

    public abstract List<BugEntity> dtoToDomainList(List<Bug> bugList);


    @AfterMapping
    void addIgnoredFieldsToDto(BugEntity bugEntity, @MappingTarget Bug bug) {
        // user story
        if (bugEntity.getUserStory() != null) {
            UserStory userStory = new UserStory();
            userStory.setId(bugEntity.getUserStory().getId());

            bug.setUserStory(userStory);
        }

        // user
        if (bugEntity.getUser() != null) {
            User user = new User();
            user.setId(bugEntity.getUser().getId());
            user.setName(bugEntity.getUser().getName());
            user.setEmail(bugEntity.getUser().getEmail());

            bug.setUser(user);
        }
    }

    @AfterMapping
    void addIgnoredFieldsToDomain(Bug bug, @MappingTarget BugEntity bugEntity) {
        // user story
        if (bug.getUserStory() != null) {
            UserStoryEntity userStoryEntity = new UserStoryEntity();
            userStoryEntity.setId(bug.getUserStory().getId());

            bugEntity.setUserStory(userStoryEntity);
        }

        // user
        if (bug.getUser() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(bug.getUser().getId());
            userEntity.setName(bug.getUser().getName());
            userEntity.setEmail(bug.getUser().getEmail());

            bugEntity.setUser(userEntity);
        }
    }

}
