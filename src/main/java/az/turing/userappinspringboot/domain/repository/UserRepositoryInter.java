package az.turing.userappinspringboot.domain.repository;

import az.turing.userappinspringboot.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryInter {

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    UserEntity save(UserEntity userEntity);

    void delete(Long id);

    boolean existsByUsername(String username);

    boolean existsById(Long id);
}
