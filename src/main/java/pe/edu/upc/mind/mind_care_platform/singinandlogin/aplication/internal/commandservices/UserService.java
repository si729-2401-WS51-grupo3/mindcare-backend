package pe.edu.upc.mind.mind_care_platform.singinandlogin.aplication.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.exceptions.UserNotFoundException;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.commands.CreateUserCommand;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.commands.UserRegisterCommand;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.User;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.infraestructure.repositories.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User registerUser(UserRegisterCommand command) {
        User user = new User();
        user.setEmail(command.email());
        user.setPassword(command.password());
        user.setName(command.name());
        user.setRole(command.role());
        user.setBirthDate(command.birthDate());
        user.setGender(command.gender());
        user.setDescription(command.description());
        return userRepository.save(user);
    }

    public User createUser(CreateUserCommand command) {
        User user = new User();
        user.setEmail(command.email());
        user.setPassword(command.password());
        user.setName(command.name());
        user.setRole(command.role());
        user.setBirthDate(command.birthDate());
        user.setGender(command.gender());
        user.setDescription(command.description());
        return userRepository.save(user);
    }

    public User updateUser(Long id, UpdateUserCommand command) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setEmail(command.email());
            user.setName(command.name());
            user.setRole(command.role());
            user.setBirthDate(command.birthDate());
            user.setGender(command.gender());
            user.setDescription(command.description());
            userRepository.save(user);
        }
        return user;
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setEmail(userDetails.getEmail());
        user.setName(userDetails.getName());
        user.setRole(userDetails.getRole());
        user.setBirthDate(userDetails.getBirthDate());
        user.setGender(userDetails.getGender());
        user.setDescription(userDetails.getDescription());
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    // Función para autenticar al usuario
    public boolean authenticateUser(String username, String password) {
        // Lógica de autenticación (puede variar según la implementación)
        // Por ejemplo, buscar el usuario por nombre de usuario y verificar la contraseña
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        } else {
            return false;
        }
    }

    // Función para actualizar la configuración de la cuenta del usuario
    public User updateUserSettings(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        // Actualizar los campos de configuración del usuario con los nuevos valores proporcionados
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        // Actualizar otros campos según sea necesario

        // Guardar el usuario actualizado en la base de datos
        return userRepository.save(user);
    }
}
