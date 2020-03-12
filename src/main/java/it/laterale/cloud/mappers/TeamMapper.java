package it.laterale.cloud.mappers;

import it.laterale.cloud.dtos.TeamDto;
import it.laterale.cloud.entities.Team;
import org.springframework.stereotype.Component;

/**
 * The type Team mapper.
 */
@Component
public class TeamMapper {

    /**
     * To dto team dto.
     *
     * @param entity the entity
     * @return the team dto
     */
    public TeamDto toDto(Team entity) {
        TeamDto dto = new TeamDto();
        dto.setName(entity.getName());
        dto.setMembers(entity.getMembers());
        dto.setPrimaryColor(entity.getPrimaryColor());
        return dto;
    }

    /**
     * To entity team.
     *
     * @param dto the dto
     * @return the team
     */
    public Team toEntity(TeamDto dto) {
        Team entity = new Team();
        entity.setName(dto.getName());
        entity.setMembers(dto.getMembers());
        entity.setPrimaryColor(dto.getPrimaryColor());
        return entity;
    }
}
