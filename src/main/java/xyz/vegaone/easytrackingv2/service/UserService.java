package xyz.vegaone.easytrackingv2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.User;
import xyz.vegaone.easytrackingv2.exception.EntityNotFoundException;
import xyz.vegaone.easytrackingv2.mapper.UserMapper;
import xyz.vegaone.easytrackingv2.repo.UserRepo;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserService {

    private UserMapper userMapper;

    private UserRepo userRepo;

    @Autowired
    public UserService(UserMapper userMapper,
                       UserRepo userRepo) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
    }

    public User createUser(User user) {

        UserEntity userEntity = userMapper.dtoToDomain(user);

        UserEntity savedUserEntity = userRepo.save(userEntity);

        return userMapper.domainToDto(savedUserEntity);

    }

    public User getUser(Long id) {

        UserEntity userEntity = userRepo.findOne(id);

        if (userEntity == null) {
            throw new EntityNotFoundException("User with id " + id + " not found.");
        }

        return userMapper.domainToDto(userEntity);
    }

    public void deleteUser(Long id) {
        userRepo.delete(id);
    }

    public User updateUser(User user) {
        UserEntity userEntity = userMapper.dtoToDomain(user);

        UserEntity savedUserEntity = userRepo.save(userEntity);

        return userMapper.domainToDto(savedUserEntity);
    }

    public List<User> findAllUsers(){
        List<UserEntity> userEntityList = Collections.emptyList();
        userEntityList = userRepo.findAll();

        return userMapper.domainToDtoList(userEntityList);
    }
}
