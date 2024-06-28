package pe.edu.upc.mind.care.platform.iam.interfaces.acl;

import org.apache.logging.log4j.util.Strings;
import pe.edu.upc.mind.care.platform.iam.domain.model.commands.SignUpCommand;
import pe.edu.upc.mind.care.platform.iam.domain.model.entities.Role;
import pe.edu.upc.mind.care.platform.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.mind.care.platform.iam.domain.model.queries.GetUserByEmailQuery;
import pe.edu.upc.mind.care.platform.iam.domain.services.UserCommandService;
import pe.edu.upc.mind.care.platform.iam.domain.services.UserQueryService;

import java.util.ArrayList;
import java.util.List;

public class IamContextFacade {

  private final UserCommandService userCommandService;
  private final UserQueryService userQueryService;

  public IamContextFacade(UserCommandService userCommandService,
      UserQueryService userQueryService) {
    this.userCommandService = userCommandService;
    this.userQueryService = userQueryService;
  }

  /**
   * Creates a user with the given Email and password.
   * @param email The Email of the user.
   * @param password The password of the user.
   * @return The id of the created user.
   */
  public Long createUser(String email, String password) {
    var signUpCommand = new SignUpCommand(email, password, List.of(Role.getDefaultRole()));
    var result = userCommandService.handle(signUpCommand);
    if (result.isEmpty()) return 0L;
    return result.get().getId();
  }

  /**
   * Creates a user with the given Email, password and roles.
   * @param email The Email of the user.
   * @param password The password of the user.
   * @param roleNames The names of the roles of the user. When a role does not exist,
   *                  it is ignored.
   * @return The id of the created user.
   */
  public Long createUser(String email, String password, List<String> roleNames) {
    var roles = roleNames != null
        ? roleNames.stream().map(Role::toRoleFromName).toList()
        : new ArrayList<Role>();
    var signUpCommand = new SignUpCommand(email, password, roles);
    var result = userCommandService.handle(signUpCommand);
    if (result.isEmpty())
      return 0L;
    return result.get().getId();
  }

  /**
   * Fetches the id of the user with the given Email.
   * @param email The Email of the user.
   * @return The id of the user.
   */
  public Long fetchUserIdByEmail(String email) {
    var getUserByEmailQuery = new GetUserByEmailQuery(email);
    var result = userQueryService.handle(getUserByEmailQuery);
    if (result.isEmpty())
      return 0L;
    return result.get().getId();
  }

  /**
   * Fetches the Email of the user with the given id.
   * @param userId The id of the user.
   * @return The Email of the user.
   */
  public String fetchEmailByUserId(Long userId) {
    var getUserByIdQuery = new GetUserByIdQuery(userId);
    var result = userQueryService.handle(getUserByIdQuery);
    if (result.isEmpty())
      return Strings.EMPTY;
    return result.get().getEmail();
  }
}
