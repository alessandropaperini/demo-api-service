package it.laterale.cloud.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.laterale.cloud.dtos.LoginOutputDto;
import it.laterale.cloud.dtos.input.ApplicationUserInputDto;
import it.laterale.cloud.dtos.input.LoginInputDto;
import it.laterale.cloud.entities.ApplicationUser;
import it.laterale.cloud.mappers.ApplicationUserMapper;
import it.laterale.cloud.security.JwtProvider;
import it.laterale.cloud.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Authentication controller.
 */
@RestController
@RequestMapping("public/authentication")
public class AuthenticationController {

    @Autowired
    private ApplicationUserService userService;

    @Autowired
    private ApplicationUserMapper mapper;

    /**
     * Signup response entity.
     *
     * @param body the body
     * @return the response entity
     */
    @PostMapping("/signup")
    @ApiOperation(value = "add user", notes = "create new user from input")
    public ResponseEntity<Void> signup(
            @ApiParam(name = "body", value = "the type ApplicationUserInputDto body request") @RequestBody ApplicationUserInputDto body) {
        userService.create(body);
        return ResponseEntity.noContent().build();
    }

    /**
     * Signin response entity.
     *
     * @param body the body
     * @return the response entity
     */
    @PostMapping
    @ApiOperation(value = "user login", notes = "perform user login")
    public ResponseEntity<LoginOutputDto> signin(
            @ApiParam(name = "body", value = "the type LoginInputDto body request") @RequestBody LoginInputDto body) {
        // verifica se l'utente è registrato su db
        ApplicationUser user = userService.getByUsernameAndPassword(body.getUsername(), body.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("user", new ObjectMapper().valueToTree(this.mapper.toDto(user)));

        String jwt = JwtProvider.createJwt(user.getUsername(), claimMap);
        LoginOutputDto dto = new LoginOutputDto();
        dto.setToken(jwt);
        return ResponseEntity.ok(dto);
    }
}
