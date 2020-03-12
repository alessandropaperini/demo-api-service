package it.laterale.cloud.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.laterale.cloud.dtos.TeamDto;
import it.laterale.cloud.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Team controller.
 */
@RestController
@RequestMapping("api/v1/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "get team", notes = "get team by id")
    public ResponseEntity<TeamDto> getById(
            @ApiParam(name = "id", value = "the unique team id") @PathVariable(name = "id") Long id
    ) {
        return ResponseEntity.ok(teamService.getById(id));
    }

    /**
     * Create response entity.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping
    @ApiOperation(value = "create", notes = "create new team")
    public ResponseEntity<Void> create(
            @ApiParam(name = "dto", value = "the body request") @RequestBody TeamDto dto
    ) {
        teamService.create(dto);
        return ResponseEntity.noContent().build();
    }
}
