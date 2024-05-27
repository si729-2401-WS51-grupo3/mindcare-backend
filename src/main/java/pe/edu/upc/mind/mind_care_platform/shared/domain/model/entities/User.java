package pe.edu.upc.mind.mind_care_platform.shared.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class User {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "La fecha de cumpleaños no puede estar vacía")
    private Date bitrhDate;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastName;

    private String description;

    @Email(message = "El correo electrónico debe tener un formato válido")
    private String email;

    @NotBlank(message = "El DNI no puede estar vacío")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    private String dni;

    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Pattern(regexp = "\\d{9}", message = "El número de teléfono debe tener 9 dígitos")
    private String phone;

    private String photoUrl;

    public User(String name, String lastName, Date bitrhDate, String description, String email, String dni, String phone, String photoUrl) {
        this.name = name;
        this.lastName = lastName;
        this.bitrhDate=bitrhDate;
        this.description = description;
        this.email = email;
        this.dni = dni;
        this.phone = phone;
        this.photoUrl = photoUrl;
    }

    public User() {
    }
    /**
     * Calculates the user age.
     * @return The user age.
     */
    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = LocalDate.of(bitrhDate.getYear(), bitrhDate.getMonth(), bitrhDate.getDay());

        Period period = Period.between(birthDate, currentDate);

        int age = period.getYears();
        return 0;
    }
    /**
     * Updates the user information.
     * @param email The new title.
     * @param phone The new description.
     * @return The updated user.
     */
    public User updateInformation(String email, String phone) {
        this.email = email;
        this.phone = phone;
        return this;
    }
}