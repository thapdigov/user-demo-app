package az.turing.userappinspringboot.domain.repository;

import az.turing.userappinspringboot.domain.entity.UserEntity;
import az.turing.userappinspringboot.model.enums.UserStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserEntity.builder()
                .userId(rs.getLong("userId"))
                .username(rs.getString("username"))
                .userStatus(UserStatus.valueOf(rs.getString("userstatus")))
                .userPassword(rs.getString("userPassword"))
                .build();
    }
}
