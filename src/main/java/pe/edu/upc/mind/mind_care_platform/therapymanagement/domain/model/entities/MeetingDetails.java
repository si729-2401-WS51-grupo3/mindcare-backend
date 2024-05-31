package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;

@Setter
@Getter
@Entity
//bounded context de search and match
public class MeetingDetails {
    @Id
    private Long id;
    private String patientName;
    private String meetingTime;
    private String psychologistName;
    private String psychologistSpecialty;
    private String meetingDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "financial_id")
    @NotNull
    private Financial financial;

    // Constructor, getters and setters...
}