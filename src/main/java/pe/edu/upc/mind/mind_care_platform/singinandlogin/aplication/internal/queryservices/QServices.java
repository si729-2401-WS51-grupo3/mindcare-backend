package pe.edu.upc.mind.mind_care_platform.singinandlogin.aplication.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.User;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.infraestructure.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class QServices {
    private final UserRepository userRepository;

    @Autowired
    public QServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    public Collection<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}