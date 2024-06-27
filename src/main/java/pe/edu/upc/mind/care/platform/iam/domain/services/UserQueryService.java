package pe.edu.upc.mind.care.platform.iam.domain.services;

import pe.edu.upc.mind.care.platform.iam.domain.model.aggregates.User;
import pe.edu.upc.mind.care.platform.iam.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.mind.care.platform.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.mind.care.platform.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
  List<User> handle(GetAllUsersQuery query);
  Optional<User> handle(GetUserByIdQuery query);
  Optional<User> handle(GetUserByUsernameQuery query);
}
