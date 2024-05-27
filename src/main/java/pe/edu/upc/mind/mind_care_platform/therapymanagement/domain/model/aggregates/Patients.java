package pe.edu.upc.mind.mindcare_platform.therapymanagement.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String description;
    private String meetingIds; // Almacena los IDs de las reuniones como una cadena de texto

}
