package az.turing.userappinspringboot.domain.entity;

import az.turing.userappinspringboot.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;
    @Column(name = "username")
    private String username;
    @ToString.Exclude
    @Column(name="userPassword")
    private String userPassword;
    @Enumerated(EnumType.STRING)
    @Column(name = "userstatus")
    private UserStatus userStatus;
}
