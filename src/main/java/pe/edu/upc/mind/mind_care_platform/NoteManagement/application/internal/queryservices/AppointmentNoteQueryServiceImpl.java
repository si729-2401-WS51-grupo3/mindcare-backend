package pe.edu.upc.mind.mind_care_platform.NoteManagement.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.aggregates.AppointmentNote;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.queries.GetAllAppointmentNotesQuery;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.queries.GetAppointmentNoteByIdQuery;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.queries.GetNotesByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.queries.GetNotesByPsychologistIdQuery;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.services.AppointmentNoteQueryService;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.infrastructure.persistence.jpa.repositories.AppointmentNoteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentNoteQueryServiceImpl implements AppointmentNoteQueryService {
    private final AppointmentNoteRepository appointmentNoteRepository;

    public AppointmentNoteQueryServiceImpl(AppointmentNoteRepository appointmentNoteRepository) {
        this.appointmentNoteRepository = appointmentNoteRepository;
    }

    @Override
    public List<AppointmentNote> handle(GetAllAppointmentNotesQuery query) {
        return appointmentNoteRepository.findAll();
    }

    @Override
    public Optional<AppointmentNote> handle(GetAppointmentNoteByIdQuery query) {
        return appointmentNoteRepository.findById(query.appointmentNoteId());
    }

    @Override
    public List<AppointmentNote> handle(GetNotesByPsychologistIdQuery query) {
        return appointmentNoteRepository.findByPsychologistId(query.psychologistId());
    }

    @Override
    public List<AppointmentNote> handle(GetNotesByPatientIdQuery query) {
        return appointmentNoteRepository.findByPatientId(query.patientId());
    }
}
