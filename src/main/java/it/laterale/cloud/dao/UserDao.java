package it.laterale.cloud.dao;

import it.laterale.cloud.entities.ApplicationUser;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * The type User dao.
 */
@Component
@Transactional(readOnly = true)
public class UserDao {

    private static final String NO_RESULT = "No Result";

    @PersistenceContext
    private EntityManager em;

    /**
     * Find by id application user.
     *
     * @param id the id
     * @return the application user
     */
    public ApplicationUser findById(Long id) {
        ApplicationUser result = em.find(ApplicationUser.class, id);
        if (result != null) {
            return result;
        } else {
            throw new EntityNotFoundException(NO_RESULT);
        }
    }

    /**
     * Find by email application user.
     *
     * @param email the email
     * @return the application user
     */
    public ApplicationUser findByEmail(String email) {
        TypedQuery<ApplicationUser> query = em.createNamedQuery("user.findByEmail", ApplicationUser.class);
        query.setParameter("email", email);
        List<ApplicationUser> result = query.getResultList();
        if (result != null && result.size() == 1) {
            return result.get(0);
        } else {
            throw new EntityNotFoundException(result == null || result.size() == 0 ? NO_RESULT : "Non unique result");
        }
    }

    /**
     * Create.
     *
     * @param entity the entity
     */
    @Transactional
    public void create(ApplicationUser entity) {
        em.persist(entity);
    }

}
