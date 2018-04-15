package xyz.vegaone.easytrackingv2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.User;
import xyz.vegaone.easytrackingv2.dto.UserStatistics;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.mapper.UserMapper;
import xyz.vegaone.easytrackingv2.repo.UserRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private UserMapper userMapper;

    private UserRepo userRepo;

    private BugService bugService;

    private TaskService taskService;

    private UserStoryService userStoryService;

    @Autowired
    public UserService(UserMapper userMapper, UserRepo userRepo, BugService bugService, TaskService taskService, UserStoryService userStoryService) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
        this.bugService = bugService;
        this.taskService = taskService;
        this.userStoryService = userStoryService;
    }

    public User createUser(User user) {

        UserEntity userEntity = userMapper.dtoToDomain(user);

        UserEntity savedUserEntity = userRepo.save(userEntity);

        return userMapper.domainToDto(savedUserEntity);

    }

    public User getUser(Long id) {

        Optional<UserEntity> userOptional = userRepo.findById(id);

        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            User user = userMapper.domainToDto(userEntity);

            return user;
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found.");
        }
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public User updateUser(User user) {
        UserEntity userEntity = userMapper.dtoToDomain(user);

        UserEntity savedUserEntity = userRepo.save(userEntity);

        return userMapper.domainToDto(savedUserEntity);
    }

    public List<User> findAllUsers() {
        List<UserEntity> userEntityList = Collections.emptyList();
        userEntityList = userRepo.findAll();

        return userMapper.domainToDtoList(userEntityList);
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
