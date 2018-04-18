package xyz.vegaone.easytrackingv2.service;

import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv2.domain.UserStoryEntity;
import xyz.vegaone.easytrackingv2.dto.UserStory;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.repo.UserStoryRepo;
import xyz.vegaone.easytrackingv2.util.MapperUtil;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserStoryService {

    private Mapper mapper;

    private MapperUtil mapperUtil;

    private UserStoryRepo userStoryRepo;

    @Autowired
    public UserStoryService(Mapper mapper,
                            MapperUtil mapperUtil,
                            UserStoryRepo userStoryRepo) {
        this.mapper = mapper;
        this.mapperUtil = mapperUtil;
        this.userStoryRepo = userStoryRepo;
    }

    public UserStory createUserStory(UserStory userStory) {
        UserStoryEntity userStoryEntity = mapper.map(userStory, UserStoryEntity.class);
        UserStoryEntity savedUserStoryEntity = userStoryRepo.save(userStoryEntity);

        return mapper.map(savedUserStoryEntity, UserStory.class);
    }

    public UserStory getUserStory(Long id) {

        Optional<UserStoryEntity> userStoryOptional = userStoryRepo.findById(id);

        UserStoryEntity userStoryEntity = userStoryOptional.orElseThrow(() ->
                new EntityNotFoundException("User story with id " + id + " not found"));

        return mapper.map(userStoryEntity, UserStory.class);

    }

    public List<UserStory> getUserStoryByProjectId(Long projectId) {

        List<UserStoryEntity> userStoryEntityList = userStoryRepo.findAllByProjectId(projectId);

        return mapperUtil.mapList(userStoryEntityList, UserStory.class);

    }

    public void deleteUserStory(Long id) {
        userStoryRepo.deleteById(id);
    }

    public UserStory updateUserStory(UserStory userStory) {
        UserStoryEntity userStoryEntity = mapper.map(userStory, UserStoryEntity.class);

        UserStoryEntity savedUserStoryEntity = userStoryRepo.save(userStoryEntity);

        return mapper.map(savedUserStoryEntity, UserStory.class);
    }

    public Long getUserStatisticsUserStories(Long id){
        return userStoryRepo.countByUserId(id);
    }

}
