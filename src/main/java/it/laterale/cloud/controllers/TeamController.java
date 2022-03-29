package it.laterale.cloud.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.laterale.cloud.dtos.TeamDto;
import it.laterale.cloud.model.User;
import it.laterale.cloud.model.Team;
import it.laterale.cloud.repositories.TeamRepository;
import it.laterale.cloud.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("team")
public class TeamController {

    private static final Logger log = LogManager.getLogger(TeamController.class);

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("{id}")
    @ApiOperation(value = "get team", notes = "get team by id")
    public Team getById(@ApiParam(name = "id", value = "the unique team id") @PathVariable(name = "id") Long id) {
        return this.teamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ApiOperation(value = "get team", notes = "get team by name")
    public Team getByName(@ApiParam(name = "name", value = "the unique team name") @RequestParam("name") String name) {
        return this.teamRepository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "create", notes = "create new team")
    public Team create(@ApiParam(name = "dto", value = "the body request") @RequestBody TeamDto dto) {
        List<User> users = this.userRepository.findAllById(dto.getUsers());
        if (users.size() < dto.getUsers().size()) {
            log.warn("Some users have not been added because they do not exist");
        }
        Team team = new Team();
        team.setName(dto.getName());
        team.setMembers(new HashSet<>(users));
        return this.teamRepository.save(team);
    }

    @PatchMapping("{id}")
    public Team addMembers(
            @ApiParam(name = "dto", value = "the body request") @RequestBody List<Long> members,
            @ApiParam(name = "id", value = "the unique team id") @PathVariable("id") Long id
    ) {
        Team team = this.teamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<User> users = this.userRepository.findAllById(members);
        if (users.size() < members.size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One or more users were not found");
        }
        team.setMembers(new HashSet<>(users));
        return this.teamRepository.save(team);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@ApiParam(name = "id", value = "the unique team id") @PathVariable("id") Long id) {
        Team entity = this.teamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.teamRepository.delete(entity);
    }
}
