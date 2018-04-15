package xyz.vegaone.easytrackingv2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv2.domain.UserStoryEntity;
import xyz.vegaone.easytrackingv2.dto.UserStory;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.mapper.UserStoryMapper;
import xyz.vegaone.easytrackingv2.repo.UserStoryRepo;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserStoryService {

    private UserStoryMapper userStoryMapper;

    private UserStoryRepo userStoryRepo;

    @Autowired
    public UserStoryService(UserStoryMapper userStoryMapper, UserStoryRepo userStoryRepo) {
        this.userStoryMapper = userStoryMapper;
        this.userStoryRepo = userStoryRepo;
    }

    public UserStory createUserStory(UserStory userStory) {
        UserStoryEntity userStoryEntity = userStoryMapper.dtoToDomain(userStory);
        UserStoryEntity savedUserStoryEntity = userStoryRepo.save(userStoryEntity);

        return userStoryMapper.domainToDto(savedUserStoryEntity);
    }

    public UserStory getUserStory(Long id) {

        Optional<UserStoryEntity> userStoryOptional = userStoryRepo.findById(id);

            UserStoryEntity userStoryEntity = userStoryOptional.orElseThrow(()->
                    new EntityNotFoundException("User story with id " + id + " not found"));
            UserStory userStory = userStoryMapper.domainToDto(userStoryEntity);

            return userStory;

    }

    public List<UserStory> getUserStoryByProjectId(Long projectId) {

        List<UserStoryEntity> userStoryEntityList = userStoryRepo.findAllByProjectId(projectId);

        return userStoryMapper.domainToDtoList(userStoryEntityList);

    }

    public void deleteUserStory(Long id) {
        userStoryRepo.deleteById(id);
    }

    public UserStory updateUserStory(UserStory userStory) {
        UserStoryEntity userStoryEntity = userStoryMapper.dtoToDomain(userStory);

        UserStoryEntity savedUserStoryEntity = userStoryRepo.save(userStoryEntity);

        UserStory savedUserStory = userStoryMapper.domainToDto(savedUserStoryEntity);

        return savedUserStory;
    }

    public Long getUserStatisticsUserStories(Long id){
        return userStoryRepo.countByUserId(id);
    }

}
