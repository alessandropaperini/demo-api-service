package it.laterale.cloud.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.laterale.cloud.dtos.UserDto;
import it.laterale.cloud.model.User;
import it.laterale.cloud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("{id}")
    @ApiOperation(value = "get user", notes = "get user by id")
    public User getById(
            @ApiParam(name = "id", value = "the unique user id") @PathVariable("id") Long id,
            @ApiParam(name = "authorization", value = "the authentication request token") @RequestHeader("Authorization") String authorization) {
        return this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ApiOperation(value = "get user by email")
    public User getByEmail(
            @ApiParam(name = "email", value = "the user email") @RequestParam(value = "email") String email,
            @ApiParam(name = "authorization", value = "the authentication request token") @RequestHeader("Authorization") String authorization) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("all")
    public Collection<User> getAll() {
        return this.userRepository.findAll();
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "create new user")
    public User create(@ApiParam(name = "userDto", value = "the body request") @RequestBody UserDto userDto) {
        return this.userRepository.save(userDto.toEntity(this.passwordEncoder));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.userRepository.delete(user);
    }
}
