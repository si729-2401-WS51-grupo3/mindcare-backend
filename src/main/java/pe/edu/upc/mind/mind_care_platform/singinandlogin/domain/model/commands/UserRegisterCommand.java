package pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.commands;

import java.time.LocalDate;

public record UserRegisterCommand(  String username,
                                    String password,
                                    String email,
                                    String role,
                                    String name,
                                    String lastName,
                                    String phone,
                                    LocalDate birthDate,
                                    String gender,
                                    String description) {
    public UserRegisterCommand {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }
    }
}