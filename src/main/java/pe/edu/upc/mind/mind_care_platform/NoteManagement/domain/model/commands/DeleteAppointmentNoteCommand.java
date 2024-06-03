package pe.edu.upc.mind.mind_care_platform.NoteManagement.domain.model.commands;

public class DeleteAppointmentNoteCommand {
    private final Long appointmentNoteId;

    public DeleteAppointmentNoteCommand(Long appointmentNoteId) {
        this.appointmentNoteId = appointmentNoteId;
    }

    public Long getAppointmentNoteId() {
        return appointmentNoteId;
    }
}
