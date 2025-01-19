package az.turing.userappinspringboot.domain.repository;

import az.turing.userappinspringboot.domain.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserEntity save(UserEntity userEntity) {
        String query = "insert into users(userId,username) values(?,?)";
        jdbcTemplate.update(query, userEntity.getUserId(), userEntity.getUsername());
        return userEntity;
    }

//    public List<UserEntity> findAll() {
//
//    }
}
