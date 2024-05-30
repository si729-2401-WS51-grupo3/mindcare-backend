package pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.aggregates;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.Psychologist;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.Patient;
public class UserAggregate {
    private Patient patient;
    private Psychologist psychologist;

    public UserAggregate(Patient patient, Psychologist psychologist) {
        this.patient = patient;
        this.psychologist = psychologist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Psychologist getPsychologist() {
        return psychologist;
    }

    public void setPsychologist(Psychologist psychologist) {
        this.psychologist = psychologist;
    }
}
