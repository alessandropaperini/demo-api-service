package it.laterale.cloud.controllers;

import it.laterale.cloud.dtos.AuthenticationDto;
import it.laterale.cloud.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public String login(@RequestBody AuthenticationDto authDto) {
        return this.authenticationService.login(authDto.getEmail(), authDto.getPassword());
    }
}
