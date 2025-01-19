package az.turing.userappinspringboot.controller;

import az.turing.userappinspringboot.domain.entity.UserEntity;
import az.turing.userappinspringboot.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/api")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

//    @GetMapping
//    public ResponseEntity<UserEntity> getAll() {
//        return ResponseEntity.ok(userRepository.findAll());
//    }


    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity entity) {
        return ResponseEntity.ok(userRepository.save(entity));
    }
}
