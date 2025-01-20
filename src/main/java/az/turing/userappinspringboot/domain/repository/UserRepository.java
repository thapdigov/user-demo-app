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
        if (userEntity.getUserId() == null) {
            String query = "insert into users(username,userPassword,userStatus) values(?,?,?)";
            jdbcTemplate.update(query, userEntity.getUsername(),
                    userEntity.getUserPassword(), userEntity.getUserStatus());
            return userEntity;
        } else {
            String query = "update users set username=?,userPassword=?,userStatus=? where userId=?";
            jdbcTemplate.update(query, userEntity.getUsername(), userEntity.getUserPassword()
                    , userEntity.getUserStatus(), userEntity.getUserId());
            return userEntity;
        }
    }

//    public List<UserEntity> findAll() {
//
//    }
}
