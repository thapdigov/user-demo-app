package az.turing.userappinspringboot.service;

import az.turing.userappinspringboot.domain.entity.UserEntity;
import az.turing.userappinspringboot.domain.repository.UserRepositoryInter;
import az.turing.userappinspringboot.exception.AlreadyExistsException;
import az.turing.userappinspringboot.exception.InvalidException;
import az.turing.userappinspringboot.exception.NotFoundException;
import az.turing.userappinspringboot.model.dto.UserDto;
import az.turing.userappinspringboot.model.enums.UserStatus;
import az.turing.userappinspringboot.model.mapper.UserMapper;
import az.turing.userappinspringboot.model.request.CreateUserRequest;
import az.turing.userappinspringboot.model.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepositoryInter userRepository;
    private final UserMapper userMapper;

    public UserDto create(CreateUserRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            log.error(" Password and confirmPassword isn't the same for {}", request.getUsername());
            throw new InvalidException("Password isn't the same!");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            log.error(" User with {} already exists!", request.getUsername());
            throw new AlreadyExistsException("User with " + request.getUsername() + " already exists!");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(request.getUsername());
        userEntity.setUserPassword(request.getPassword());
        userEntity.setUserStatus(UserStatus.ACTIVE);
        log.info("User created {}",userEntity);
        return userMapper.toDto(userRepository.save(userEntity));
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().
                map(userMapper::toDto).collect(Collectors.toList());
    }


    public UserDto findById(Long id) {
        log.error("User not found with {}",id);
        return userRepository.findById(id).map(userMapper::toDto).
                orElseThrow(() -> new NotFoundException("User with " + id + " not found!"));
    }


    public UserDto findByUsername(String username) {
        log.error("User not found with {}",username);
        return userMapper.toDto(userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with " + username + " not found!")));
    }

    public UserDto update(Long id, UpdateUserRequest request) {
        log.error("User not found with {}",id);
        UserEntity updatedEntity = userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User with " + id + " not found!"));
        updatedEntity.setUsername(request.getUsername());
        updatedEntity.setUserPassword(request.getPassword());
        log.info("User updated succesfuly! {}",updatedEntity);
        return userMapper.toDto(userRepository.save(updatedEntity));
    }

    public void deleteById(Long id) {
        //soft delete
        log.error("User not found with {}",id);
        UserEntity deletedEntity = userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User with " + id + " not found!"));
        log.info("User deleted succesfuly! {}",deletedEntity);
        deletedEntity.setUserStatus(UserStatus.DELETE);
        //hard delete
        // userRepository.deleteById(id);
    }

    public UserDto updatePut(Long id, UserStatus status) {
        log.error("User not found with {}",id);
        UserEntity updatedEntity = userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User with " + id + " not found!"));
        updatedEntity.setUserStatus(status);
        log.info("User updated succesfuly! {}",updatedEntity);
        return userMapper.toDto(userRepository.save(updatedEntity));
    }
}
