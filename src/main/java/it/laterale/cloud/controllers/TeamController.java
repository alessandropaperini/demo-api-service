package it.laterale.cloud.controllers;

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
    public Team getById(@PathVariable(name = "id") Long id) {
        return this.teamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Team getByName(@RequestParam("name") String name) {
        return this.teamRepository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Team create(@RequestBody TeamDto dto) {
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
    public Team addMembers(@PathVariable("id") Long id, @RequestBody List<Long> members) {
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
    public void delete(@PathVariable("id") Long id) {
        Team entity = this.teamRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        this.teamRepository.delete(entity);
    }
}
