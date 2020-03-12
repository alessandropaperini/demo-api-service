package it.laterale.cloud.services;

import it.laterale.cloud.dtos.TeamDto;
import it.laterale.cloud.entities.Team;
import it.laterale.cloud.mappers.TeamMapper;
import it.laterale.cloud.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Team service.
 */
@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public TeamDto getById(Long id) {
        Optional<Team> resultOpt = teamRepository.findById(id);
        return resultOpt.isPresent() ? teamMapper.toDto(resultOpt.get()) : null;
    }

    /**
     * Create.
     *
     * @param dto the dto
     */
    public void create(TeamDto dto) {
        teamRepository.save(teamMapper.toEntity(dto));
    }
}
