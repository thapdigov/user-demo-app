package az.turing.userappinspringboot.service;

import az.turing.userappinspringboot.domain.repository.UserRepositoryInter;
import az.turing.userappinspringboot.exception.NotFoundException;
import az.turing.userappinspringboot.model.dto.UserDto;
import az.turing.userappinspringboot.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositoryInter userRepository;
    private final UserMapper userMapper;

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().
                map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto).
                orElseThrow(() -> new NotFoundException("User with " + id + " not found!"));
    }
}
