package com.amr.project.webapp.controller;

import com.amr.project.mapper.UserMapper;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class RestUserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userMapper.toUserDtos(userService.findAll()));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        userService.save(userMapper.toUser(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return ResponseEntity.ok(userMapper.toUserDto(user.orElse(null)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setId(id);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
