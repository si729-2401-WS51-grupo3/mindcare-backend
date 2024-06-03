package pe.edu.upc.mind.mind_care_platform.NoteManagement.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.aggregates.AppointmentNote;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.commands.*;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.services.AppointmentNoteCommandService;
import pe.edu.upc.mind.mind_care_platform.NoteManagement.infrastructure.persistence.jpa.repositories.AppointmentNoteRepository;

import java.util.Optional;

@Service
public class AppointmentNoteCommandServiceImpl implements AppointmentNoteCommandService {
    private final AppointmentNoteRepository appointmentNoteRepository;

    public AppointmentNoteCommandServiceImpl(AppointmentNoteRepository appointmentNoteRepository) {
        this.appointmentNoteRepository = appointmentNoteRepository;
    }

    @Override
    public AppointmentNote handle(CreateAppointmentNoteCommand command) {
        var appointmentNote = new AppointmentNote(command.psychologistId(), command.patientId(), command.appointment(), command.note());
        appointmentNoteRepository.save(appointmentNote);
        return appointmentNote;
    }

    @Override
    public Optional<AppointmentNote> handle(UpdateNoteContentCommand command) {
        if (!appointmentNoteRepository.existsById(command.appointmentNoteId())) {
            throw new IllegalArgumentException("Note does not exist");
        }
        var appointmentNote = appointmentNoteRepository.findById(command.appointmentNoteId()).get();
        appointmentNote.updateNoteContent(command.content());
        appointmentNoteRepository.save(appointmentNote);
        return Optional.of(appointmentNote);
    }

    @Override
    public void handle(AssignPsychologistCommand command) {
        var appointmentNote = appointmentNoteRepository.findById(command.appointmentNoteId())
                .orElseThrow(() -> new IllegalArgumentException("Note does not exist"));
        appointmentNote.assignPsychologist(command.psychologistId());
        appointmentNoteRepository.save(appointmentNote);
    }

    @Override
    public void handle(AssignPatientCommand command) {
        var appointmentNote = appointmentNoteRepository.findById(command.appointmentNoteId())
                .orElseThrow(() -> new IllegalArgumentException("Note does not exist"));
        appointmentNote.assignPatient(command.patientId());
        appointmentNoteRepository.save(appointmentNote);
    }

    @Override
    public void handle(DeleteAppointmentNoteCommand command) {
        appointmentNoteRepository.deleteById(command.getAppointmentNoteId());
    }
}
