package it.laterale.cloud.controllers;

import it.laterale.cloud.dtos.LoginOutputDto;
import it.laterale.cloud.dtos.input.ApplicationUserInputDto;
import it.laterale.cloud.dtos.input.LoginInputDto;
import it.laterale.cloud.entities.ApplicationUser;
import it.laterale.cloud.security.JwtProvider;
import it.laterale.cloud.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Authentication controller.
 */
@RestController
@RequestMapping("public/authentication")
public class AuthenticationController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ApplicationUserService userService;

    /**
     * Signin response entity.
     *
     * @param body the body
     * @return the response entity
     */
    @PostMapping("/signin")
    public ResponseEntity<Void> signin(@RequestBody ApplicationUserInputDto body) {
        userService.create(body);
        return ResponseEntity.noContent().build();
    }

    /**
     * Authenticate response entity.
     *
     * @param body the body
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<LoginOutputDto> authenticate(@RequestBody LoginInputDto body) {
        // verifica se l'utente è registrato su db
        ApplicationUser user = userService.getByUsernameAndPassword(body.getUsername(), body.getPassword());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        // se voluto si può inserire alcune informazioni dell'utente nel jwt modificando il metodo createJwt().

        String jwt = jwtProvider.createJwt(user.getId());
        LoginOutputDto dto = new LoginOutputDto();
        dto.setToken(jwt);
        return ResponseEntity.ok(dto);
    }
}