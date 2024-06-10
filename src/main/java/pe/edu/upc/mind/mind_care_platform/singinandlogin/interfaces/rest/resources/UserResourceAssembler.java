package pe.edu.upc.mind.mind_care_platform.singinandlogin.interfaces.rest.resources;

import org.springframework.stereotype.Component;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.User;
@Component
public class UserResourceAssembler {

    public RUser toResource(User user) {
        return new RUser(user);
    }
}
