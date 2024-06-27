package pe.edu.upc.mind.care.platform.iam.domain.services;


import pe.edu.upc.mind.care.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
