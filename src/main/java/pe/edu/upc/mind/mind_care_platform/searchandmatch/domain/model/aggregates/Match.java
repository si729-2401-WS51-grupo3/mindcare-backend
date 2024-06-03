package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.aggregates;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Reservation;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetPsychologistIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.PatientId;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.Psychologist;

@Getter
@Setter
@Entity
public class Match extends AbstractAggregateRoot<Match> {

    @Id
    private Long id;
    @Embedded
    private PatientId patientId;
    @Embedded
    private Psychologist psychologistId;
    private Reservation reservation;

    public Match() {
    }
    public Long getPatientId(Match match, GetPatientIdQuery query) {
        return match.getPatientId().patientId();
    }
    public Long getPsychologistId(Match match, GetPsychologistIdQuery query) {
        return match.getPsychologistId().profileId();
    }
}