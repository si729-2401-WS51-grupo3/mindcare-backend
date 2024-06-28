package pe.edu.upc.mind.care.platform.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mind.care.platform.iam.domain.model.aggregates.User;

import java.util.Optional;

/**
 * This interface is responsible for providing the User entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
  /**
   * This method is responsible for finding the user by Email.
   * @param email The Email.
   * @return The user object.
   */
  Optional<User> findByEmail(String email);

  /**
   * This method is responsible for checking if the user exists by Email.
   * @param email The Email.
   * @return True if the user exists, false otherwise.
   */
  boolean existsByEmail(String email);
}
