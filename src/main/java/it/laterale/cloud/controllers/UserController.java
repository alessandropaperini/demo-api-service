package it.laterale.cloud.controllers;

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
    public User getById(@PathVariable("id") Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public User getByEmail(@RequestParam(value = "email") String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("all")
    public Collection<User> getAll() {
        return this.userRepository.findAll();
    }

    @PostMapping
    public User create(@RequestBody UserDto userDto) {
        return this.userRepository.save(userDto.toEntity(this.passwordEncoder));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.userRepository.delete(user);
    }
}
