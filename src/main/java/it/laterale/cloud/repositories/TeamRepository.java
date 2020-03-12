package it.laterale.cloud.repositories;

import it.laterale.cloud.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Team repository.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
