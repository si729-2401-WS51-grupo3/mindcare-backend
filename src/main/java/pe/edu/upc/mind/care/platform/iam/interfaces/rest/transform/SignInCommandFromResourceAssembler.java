package pe.edu.upc.mind.care.platform.iam.interfaces.rest.transform;

import pe.edu.upc.mind.care.platform.iam.domain.model.commands.SignInCommand;
import pe.edu.upc.mind.care.platform.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

  public static SignInCommand toCommandFromResource(SignInResource signInResource) {
    return new SignInCommand(signInResource.username(), signInResource.password());
  }
}
