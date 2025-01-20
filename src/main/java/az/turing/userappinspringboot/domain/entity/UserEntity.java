package az.turing.userappinspringboot.domain.entity;

import az.turing.userappinspringboot.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    private Long userId;
    private String username;
    private String userPassword;
    private UserStatus userStatus;
}
