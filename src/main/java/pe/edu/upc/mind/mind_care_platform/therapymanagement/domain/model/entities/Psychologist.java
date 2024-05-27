package pe.edu.upc.mind.mindcare_platform.therapymanagement.domain.model.entities;

import jakarta.persistence.*;
import java.util.List;
@Entity
public class Psychologist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String description;
    private String speciality;

    @OneToMany(mappedBy = "psychologist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meeting> meetings;
}