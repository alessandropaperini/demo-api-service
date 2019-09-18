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

    public Optional<ApplicationUser> findByEmail(String email);

    public Collection<ApplicationUser> findByOrderByAgeAsc();

    public Collection<ApplicationUser> findByAgeGreaterThan(Integer age);

    @Query("from ApplicationUser u where u.name = :name ")
    public Collection<ApplicationUser> findByName(@Param("name") String name);
}
