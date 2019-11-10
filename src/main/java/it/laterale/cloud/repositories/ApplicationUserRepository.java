package it.laterale.cloud.repositories;

import it.laterale.cloud.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * The interface Application user repository.
 */
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    public Optional<ApplicationUser> findByEmail(String email);

    /**
     * Find by order by age asc collection.
     *
     * @return the collection
     */
    public Collection<ApplicationUser> findByOrderByAgeAsc();

    /**
     * Find by age greater than collection.
     *
     * @param age the age
     * @return the collection
     */
    public Collection<ApplicationUser> findByAgeGreaterThan(Integer age);

    /**
     * Find by name collection.
     *
     * @param name the name
     * @return the collection
     */
    @Query("from ApplicationUser u where u.name = :name ")
    public Collection<ApplicationUser> findByName(@Param("name") String name);
}
