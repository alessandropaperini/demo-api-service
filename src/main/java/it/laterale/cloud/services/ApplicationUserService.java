package it.laterale.cloud.services;

import it.laterale.cloud.dao.UserDao;
import it.laterale.cloud.dtos.ApplicationUserDto;
import it.laterale.cloud.dtos.input.ApplicationUserInputDto;
import it.laterale.cloud.entities.ApplicationUser;
import it.laterale.cloud.mappers.ApplicationUserMapper;
import it.laterale.cloud.repositories.ApplicationUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Application user service.
 */
@Service
@Slf4j
public class ApplicationUserService {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private ApplicationUserMapper applicationUserMapper;

    /**
     * Create void.
     *
     * @param dto the dto
     * @return the void
     */
    public void create(ApplicationUserInputDto dto) {
        this.applicationUserRepository.save(this.applicationUserMapper.toEntity(dto));
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public ApplicationUserDto getById(Long id) {
        Optional<ApplicationUser> entityOpt = this.applicationUserRepository.findById(id);
        return entityOpt.map(applicationUser -> this.applicationUserMapper.toDto(applicationUser)).orElse(null);
    }

}
