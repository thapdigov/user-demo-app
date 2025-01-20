package az.turing.userappinspringboot.domain.repository;

import az.turing.userappinspringboot.domain.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryInMemory implements UserRepositoryInter {

    private final List<UserEntity> USERS = new ArrayList<>();
    private final AtomicLong atomicLong = new AtomicLong();

    @Override
    public List<UserEntity> findAll() {
        return USERS;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return USERS.stream().filter(userEntity -> userEntity.getUserId().equals(id)).findFirst();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        if (userEntity.getUserId() == null) {
            userEntity.setUserId(atomicLong.incrementAndGet());
            USERS.add(userEntity);
            return userEntity;
        } else {
            UserEntity userEntity1 = findById(userEntity.getUserId()).get();
            userEntity1.setUsername(userEntity.getUsername());
            userEntity1.setUserPassword(userEntity.getUserPassword());
            USERS.add(userEntity1);
            return userEntity1;
        }
    }

    @Override
    public void deleteById(Long id) {
        USERS.stream().filter(userEntity -> userEntity.getUserId().equals(id)).findFirst().ifPresent(USERS::remove);
    }

    @Override
    public boolean existsByUsername(String username) {
        return USERS.stream().anyMatch(userEntity -> userEntity.getUsername().equals(username));
    }

    @Override
    public boolean existsById(Long id) {
        return USERS.stream().anyMatch(userEntity -> userEntity.getUserId().equals(id));
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return USERS.stream().filter(userEntity -> userEntity.getUsername().
                equals(username)).findFirst();
    }
}
