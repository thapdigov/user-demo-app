package az.turing.userappinspringboot.domain.repository;

import az.turing.userappinspringboot.domain.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryInPostgres implements UserRepositoryInter {
    private final JdbcTemplate jdbcTemplate;

    public List<UserEntity> findAll() {
        return jdbcTemplate.query(UserQuery.findAll, new UserRowMapper());
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return findAll().stream().filter(userEntity -> userEntity.getUserId().equals(id)).findFirst();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        if (userEntity.getUserId() == null) {
            jdbcTemplate.update(UserQuery.insert, userEntity.getUsername()
                    , userEntity.getUserPassword(), userEntity.getUserStatus());
            return userEntity;
        } else {
            jdbcTemplate.update(UserQuery.update, userEntity.getUsername()
                    , userEntity.getUserPassword(), userEntity.getUserStatus(), userEntity.getUserId());
            return userEntity;
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return findAll().stream().anyMatch(userEntity -> userEntity.getUsername().equals(username));
    }

    @Override
    public boolean existsById(Long id) {
        return findAll().stream().anyMatch(userEntity -> userEntity.getUserId().equals(id));
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return findAll().stream().filter(userEntity -> userEntity.getUsername().equals(username)).findFirst();
    }

    @Override
    public void deleteById(Long id) {
        findAll().stream().filter(userEntity -> false).findFirst().ifPresent(findAll()::remove);
    }
}