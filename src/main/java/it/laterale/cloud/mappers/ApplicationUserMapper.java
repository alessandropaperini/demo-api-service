package it.laterale.cloud.mappers;

import it.laterale.cloud.dtos.ApplicationUserDto;
import it.laterale.cloud.dtos.input.ApplicationUserInputDto;
import it.laterale.cloud.entities.ApplicationUser;
import org.springframework.stereotype.Component;

/**
 * The type Application user mapper.
 */
@Component
public class ApplicationUserMapper {

    /**
     * To dto application user dto.
     *
     * @param entity the entity
     * @return the application user dto
     */
    public ApplicationUserDto toDto(ApplicationUser entity) {
        ApplicationUserDto dto = new ApplicationUserDto();
        dto.setAge(entity.getAge());
        dto.setEmail(entity.getEmail());
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        dto.setUsername(entity.getUsername());
        return dto;
    }

    /**
     * To entity application user.
     *
     * @param dto the dto
     * @return the application user
     */
    public ApplicationUser toEntity(ApplicationUserDto dto) {
        ApplicationUser entity = new ApplicationUser();
        entity.setId(entity.getId());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());
        return entity;
    }

    /**
     * To entity application user.
     *
     * @param dto the dto
     * @return the application user
     */
    public ApplicationUser toEntity(ApplicationUserInputDto dto) {
        ApplicationUser entity = new ApplicationUser();
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());
        return entity;
    }
}
