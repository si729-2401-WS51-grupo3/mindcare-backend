package pe.edu.upc.mind.care.platform.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import pe.edu.upc.mind.care.platform.iam.application.internal.outboundservices.hashing.HashingService;
import pe.edu.upc.mind.care.platform.iam.application.internal.outboundservices.tokens.TokenService;
import pe.edu.upc.mind.care.platform.iam.domain.model.aggregates.User;
import pe.edu.upc.mind.care.platform.iam.domain.model.commands.SignInCommand;
import pe.edu.upc.mind.care.platform.iam.domain.model.commands.SignUpCommand;
import pe.edu.upc.mind.care.platform.iam.domain.services.UserCommandService;
import pe.edu.upc.mind.care.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.edu.upc.mind.care.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;


@Service
public class UserCommandServiceImpl implements UserCommandService {

  private final UserRepository userRepository;
  private final HashingService hashingService;
  private final TokenService tokenService;

  private final RoleRepository roleRepository;

  public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService,
      TokenService tokenService, RoleRepository roleRepository) {

    this.userRepository = userRepository;
    this.hashingService = hashingService;
    this.tokenService = tokenService;
    this.roleRepository = roleRepository;
  }

  /**
   * Handle the sign-in command
   * <p>
   *     This method handles the {@link SignInCommand} command and returns the user and the token.
   * </p>
   * @param command the sign-in command containing the Email and password
   * @return and optional containing the user matching the Email and the generated token
   * @throws RuntimeException if the user is not found or the password is invalid
   */
  @Override
  public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
    var user = userRepository.findByEmail(command.email());
    if (user.isEmpty())
      throw new RuntimeException("User not found");
    if (!hashingService.matches(command.password(), user.get().getPassword()))
      throw new RuntimeException("Invalid password");

    var token = tokenService.generateToken(user.get().getEmail());
    return Optional.of(ImmutablePair.of(user.get(), token));
  }

  /**
   * Handle the sign-up command
   * <p>
   *     This method handles the {@link SignUpCommand} command and returns the user.
   * </p>
   * @param command the sign-up command containing the Email and password
   * @return the created user
   */
  @Override
  public Optional<User> handle(SignUpCommand command) {
    if (userRepository.existsByEmail(command.email()))
      throw new RuntimeException("Email already exists");
    var roles = command.roles().stream()
        .map(role ->
            roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role name not found")))
        .toList();
    var user = new User(command.email(), hashingService.encode(command.password()), roles);
    userRepository.save(user);
    return userRepository.findByEmail(command.email());
  }
}
