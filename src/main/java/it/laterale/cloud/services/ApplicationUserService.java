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

import javax.persistence.EntityNotFoundException;

/**
 * The type Application user service.
 */
@Service
@Slf4j
public class ApplicationUserService {

    private static final String ERROR_EXECUTING_QUERY = "Error executing query";

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private ApplicationUserMapper applicationUserMapper;

    @Autowired
    private UserDao userDao;

    /**
     * Create void.
     *
     * @param dto the dto
     * @return the void
     */
    public void create(ApplicationUserInputDto dto) {
        //this.applicationUserRepository.save(this.applicationUserMapper.toEntity(dto));
        this.userDao.create(this.applicationUserMapper.toEntity(dto));
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public ApplicationUserDto getById(Long id) {
        //Optional<ApplicationUser> entityOpt = this.applicationUserRepository.findById(id);
        //return entityOpt.map(applicationUser -> this.applicationUserMapper.toDto(applicationUser)).orElse(null);
        try {
            return applicationUserMapper.toDto(userDao.findById(id));
        } catch (EntityNotFoundException e) {
            log.error(ERROR_EXECUTING_QUERY, e);
            return null;
        }
    }

    /**
     * Gets by email using DAO.
     *
     * @param email the email
     * @return the by email
     */
    public ApplicationUserDto getByEmail(String email) {
        try {
            return this.applicationUserMapper.toDto(this.userDao.findByEmail(email));
        } catch (EntityNotFoundException e) {
            log.error(ERROR_EXECUTING_QUERY, e);
            return null;
        }
    }

    /**
     * Gets by username and password.
     *
     * @param username the username
     * @param password the password
     * @return the by username and password
     */
    public ApplicationUser getByUsernameAndPassword(String username, String password) {
        return applicationUserRepository.findByUsernameAndPassword(username, password);
    }

}
