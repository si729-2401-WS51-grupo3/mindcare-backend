package pe.edu.upc.mind.mindcare_platform.therapymanagement.domain.model.entities;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String description;

    //Aca con el mappedBy se indica que la relación es bidireccional
    //y con el cascade se indica que si se elimina un paciente se eliminan todas sus reuniones
    //y con el orphanRemoval se indica que si se elimina una reunión se elimina eso en la base de datos
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    //Hacemos una lista porque un paciente puede tener muchas reuniones

    private List<Meeting> meetings;
}