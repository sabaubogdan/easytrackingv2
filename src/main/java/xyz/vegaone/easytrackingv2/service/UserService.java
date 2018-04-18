package xyz.vegaone.easytrackingv2.service;

import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import xyz.vegaone.easytrackingv2.domain.OrganizationEntity;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.Organization;
import xyz.vegaone.easytrackingv2.dto.User;
import xyz.vegaone.easytrackingv2.dto.UserStatistics;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.repo.UserRepo;
import xyz.vegaone.easytrackingv2.util.MapperUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private Mapper mapper;

    private MapperUtil mapperUtil;

    private UserRepo userRepo;

    private BugService bugService;

    private TaskService taskService;

    private UserStoryService userStoryService;

    @Autowired
    public UserService(Mapper mapper,
                       MapperUtil mapperUtil,
                       UserRepo userRepo,
                       BugService bugService,
                       TaskService taskService,
                       UserStoryService userStoryService) {
        this.mapper = mapper;
        this.mapperUtil = mapperUtil;
        this.userRepo = userRepo;
        this.bugService = bugService;
        this.taskService = taskService;
        this.userStoryService = userStoryService;
    }

    public User createUser(User user) {

        UserEntity userEntity = mapper.map(user, UserEntity.class);

        UserEntity savedUserEntity = userRepo.save(userEntity);

        return mapper.map(savedUserEntity, User.class);

    }

    public User getUser(Long id) {

        Optional<UserEntity> userOptional = userRepo.findById(id);

        UserEntity userEntity = userOptional.orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found."));

        return mapper.map(userEntity, User.class);

    }

    public List<User> getUserAllUsersbyOrganizationId(Long id) {

        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setId(id);

        List<UserEntity> userEntityList = userRepo.findAllByOrganization(organizationEntity);

        if (CollectionUtils.isEmpty(userEntityList)) {
            throw new EntityNotFoundException("No users found for organization " + id);
        }

        return mapperUtil.mapList(userEntityList, User.class);

    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public User updateUser(User user) {
        UserEntity userEntity = mapper.map(user, UserEntity.class);

        UserEntity savedUserEntity = userRepo.save(userEntity);

        return mapper.map(savedUserEntity, User.class);
    }

    public List<User> findAllUsers() {
        List<UserEntity> userEntityList = Collections.emptyList();
        userEntityList = userRepo.findAll();

        return mapperUtil.mapList(userEntityList, User.class);
    }

    public UserStatistics getUserStatistics(Long id){

        UserStatistics userStatistics = new UserStatistics();

        User user = getUser(id);

        userStatistics.setEmail(user.getEmail());
        userStatistics.setName(user.getName());
        userStatistics.setOrganization(user.getOrganization());
        userStatistics.setNumberOfBugs(bugService.getUserStatisticsBug(id));
        userStatistics.setNumberOfTasks(taskService.getUserStatisticsTasks(id));
        userStatistics.setNumberOfUserStories(userStoryService.getUserStatisticsUserStories(id));

        return userStatistics;
    }
}
