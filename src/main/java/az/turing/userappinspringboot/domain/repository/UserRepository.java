package az.turing.userappinspringboot.domain.repository;

import az.turing.userappinspringboot.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    UserEntity save(UserEntity userEntity);

    boolean existsByUsername(String username);

    boolean existsById(Long id);

    Optional<UserEntity> findByUsername(String username);

    void deleteById(Long id);
}
