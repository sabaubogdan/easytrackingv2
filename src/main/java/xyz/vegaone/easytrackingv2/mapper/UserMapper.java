package xyz.vegaone.easytrackingv2.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytrackingv2.domain.UserEntity;
import xyz.vegaone.easytrackingv2.dto.User;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    User domainToDto(UserEntity entity);

    UserEntity dtoToDomain(User user);

    List<User> domainToDtoList (List<UserEntity> userEntityList);

    List<UserEntity> dtoToDomainList(List<User> userList);

}
