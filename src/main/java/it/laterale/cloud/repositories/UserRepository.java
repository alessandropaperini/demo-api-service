package it.laterale.cloud.repositories;

import it.laterale.cloud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Collection<User> findByOrderByAgeAsc();

    Collection<User> findByAgeGreaterThan(Integer age);

    Collection<User> findByName(String name);
}
