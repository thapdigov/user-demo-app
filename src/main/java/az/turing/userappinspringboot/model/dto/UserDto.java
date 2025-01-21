package az.turing.userappinspringboot.model.dto;

import az.turing.userappinspringboot.model.enums.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    private String username;
    private String userPassword;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
}
