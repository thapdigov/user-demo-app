package az.turing.userappinspringboot.service;

import az.turing.userappinspringboot.domain.entity.UserEntity;
import az.turing.userappinspringboot.domain.repository.UserRepositoryInter;
import az.turing.userappinspringboot.exception.AlreadyExistsException;
import az.turing.userappinspringboot.exception.NotFoundException;
import az.turing.userappinspringboot.model.dto.UserDto;
import az.turing.userappinspringboot.model.enums.UserStatus;
import az.turing.userappinspringboot.model.mapper.UserMapper;
import az.turing.userappinspringboot.model.request.CreateUserRequest;
import az.turing.userappinspringboot.model.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositoryInter userRepository;
    private final UserMapper userMapper;

    public UserDto create(CreateUserRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Password isn't the same!");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AlreadyExistsException("User with " + request.getUsername() + " already exists!");
        }
        return userMapper.toDto(userRepository.save(userMapper.toEnt(request)));
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().
                map(userMapper::toDto).collect(Collectors.toList());
    }


    public UserDto findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto).
                orElseThrow(() -> new NotFoundException("User with " + id + " not found!"));
    }


    public UserDto findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User with " + username + " not found!")));
    }

    public UserDto update(Long id, UpdateUserRequest request) {
        UserEntity updatedEntity = userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User with " + id + " not found!"));
        updatedEntity.setUsername(request.getUsername());
        updatedEntity.setUserPassword(request.getPassword());
        return userMapper.toDto(userRepository.save(updatedEntity));
    }

    public void deleteById(Long id) {
        //soft delete
        UserEntity deletedEntity = userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User with " + id + " not found!"));
        deletedEntity.setUserStatus(UserStatus.DELETE);
        //hard delete
       // userRepository.deleteById(id);
    }

    public UserDto updatePut(Long id, UserStatus status) {
        UserEntity updatedEntity = userRepository.findById(id).
                orElseThrow(() -> new NotFoundException("User with " + id + " not found!"));
        updatedEntity.setUserStatus(status);
        return userMapper.toDto(userRepository.save(updatedEntity));
    }
}
