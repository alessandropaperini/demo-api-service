package it.laterale.cloud.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.laterale.cloud.dtos.ApplicationUserDto;
import it.laterale.cloud.dtos.input.ApplicationUserInputDto;
import it.laterale.cloud.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Application user controller.
 */
@RestController
@RequestMapping("api/v1/users")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    /**
     * Gets by id.
     *
     * @param id            the id
     * @param authorization the authorization
     * @return the by id
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "get user", notes = "get user by id")
    public ResponseEntity<ApplicationUserDto> getById(
            @ApiParam(name = "id", value = "the unique user id") @PathVariable("id") Long id,
            @ApiParam(name = "authorization", value = "the authentication request token") @RequestHeader("Authorization") String authorization) {
        ApplicationUserDto result = this.applicationUserService.getById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    /**
     * Gets by email.
     *
     * @param email         the email
     * @param authorization the authorization
     * @return the by email
     */
    @GetMapping
    @ApiOperation(value = "get user by email")
    public ResponseEntity<ApplicationUserDto> getByEmail(
            @ApiParam(name = "email", value = "the user email") @RequestParam(value = "email") String email,
            @ApiParam(name = "authorization", value = "the authentication request token") @RequestHeader("Authorization") String authorization) {
        ApplicationUserDto result = this.applicationUserService.getByEmail(email);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
}
