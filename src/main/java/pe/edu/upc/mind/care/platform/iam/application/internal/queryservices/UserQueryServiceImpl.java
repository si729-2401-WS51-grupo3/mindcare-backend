package pe.edu.upc.mind.care.platform.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.care.platform.iam.domain.model.aggregates.User;
import pe.edu.upc.mind.care.platform.iam.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.mind.care.platform.iam.domain.model.queries.GetUserByEmailQuery;
import pe.edu.upc.mind.care.platform.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.mind.care.platform.iam.domain.services.UserQueryService;
import pe.edu.upc.mind.care.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link UserQueryService} interface.
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
  private final UserRepository userRepository;

  /**
   * Constructor.
   *
   * @param userRepository {@link UserRepository} instance.
   */
  public UserQueryServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * This method is used to handle {@link GetAllUsersQuery} query.
   * @param query {@link GetAllUsersQuery} instance.
   * @return {@link List} of {@link User} instances.
   * @see GetAllUsersQuery
   */
  @Override
  public List<User> handle(GetAllUsersQuery query) {
    return userRepository.findAll();
  }

  /**
   * This method is used to handle {@link GetUserByIdQuery} query.
   * @param query {@link GetUserByIdQuery} instance.
   * @return {@link Optional} of {@link User} instance.
   * @see GetUserByIdQuery
   */
  @Override
  public Optional<User> handle(GetUserByIdQuery query) {
    return userRepository.findById(query.userId());
  }

  /**
   * This method is used to handle {@link GetUserByEmailQuery} query.
   * @param query {@link GetUserByEmailQuery} instance.
   * @return {@link Optional} of {@link User} instance.
   * @see GetUserByEmailQuery
   */
  @Override
  public Optional<User> handle(GetUserByEmailQuery query) {
    return userRepository.findByEmail(query.email());
  }
}
