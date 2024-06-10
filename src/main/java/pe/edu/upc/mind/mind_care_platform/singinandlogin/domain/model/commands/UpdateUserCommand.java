package pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.commands;

import java.time.LocalDate;

public record UpdateUserCommand(
        String name,
        String email,
        String phoneNumber,
        String role,
        LocalDate birthDate,
        String gender,
        String description
) {}