package it.laterale.cloud.dtos;

import it.laterale.cloud.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDto {

    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;

    public User toEntity(PasswordEncoder encoder) {
        User user = new User();
        user.setAge(this.age);
        user.setEmail(this.email);
        user.setName(this.name);
        user.setPassword(encoder.encode(this.password));
        user.setSurname(this.surname);
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
