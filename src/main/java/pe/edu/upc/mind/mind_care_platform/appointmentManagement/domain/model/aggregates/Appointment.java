package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities.AppointmentDetail;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.MeetingType;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects.PsychologistId;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.security.SecureRandom;

import java.util.Date;
import java.util.List;

@Getter
@Entity
public class Appointment extends AbstractAggregateRoot<Appointment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Embedded
    private PsychologistId psychologistId;

    private MeetingType type;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AppointmentDetail> appointmentDetails;

    public Appointment() {
        this.title = "";
        this.psychologistId = new PsychologistId();
    }
    public Appointment(String title, String type, PsychologistId psychologistId) {
        this.id = new SecureRandom().nextLong();
        this.title = title;
        this.type = MeetingType.valueOf(type);
        this.psychologistId = psychologistId;
    }

    public void updateTitle(String title) {
        this.title = title;
    }
    public void updateType(String type) {
        this.type = MeetingType.valueOf(type);
    }
    public void updatePsychologistId(PsychologistId psychologistId) {
        this.psychologistId = psychologistId;
    }
public void addAppointmentDetail(AppointmentDetail appointmentDetail) {
        appointmentDetails.add(appointmentDetail);
    }
}
