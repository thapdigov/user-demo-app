package az.turing.userappinspringboot.model.mapper;

import az.turing.userappinspringboot.domain.entity.UserEntity;
import az.turing.userappinspringboot.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEnt(UserDto userDto) {
        return UserEntity.builder()
                .userId(userDto.getUserId())
                .username(userDto.getUsername())
                .userPassword(userDto.getUserPassword())
                .userStatus(userDto.getUserStatus())
                .build();
    }

    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .userPassword(userEntity.getUserPassword())
                .userStatus(userEntity.getUserStatus())
                .build();
    }
}
