package pe.edu.upc.mind.mindcare_platform.therapymanagement.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.upc.mind.mindcare_platform.therapymanagement.domain.model.entities.Meeting;
import pe.edu.upc.mind.mindcare_platform.therapymanagement.domain.model.valueobjects.MeetingStatus;
import pe.edu.upc.mind.mindcare_platform.therapymanagement.infraestructure.persistence.jpa.repositories.MeetingRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public class MeetingService {
    //El autowired sirve para inyectar dependencias como el MeetingRepository que es una interfaz que extiende de JpaRepository
    @Autowired
    private MeetingRepository meetingRepository;

    //El metodo save sirve para guardar una reunion en la base de datos
    public Meeting scheduleMeeting(Meeting meeting) {
        meeting.setStatus(MeetingStatus.SCHEDULED);
        return meetingRepository.save(meeting);
    }

    //El metodo reprogramMeeting sirve para reprogramar una reunion en la base de datos
    //y lo hace cambiando la fecha y la hora de la reunion
    public Meeting reprogramMeeting(Long meetingId, LocalDate newDate, LocalTime newTime) {
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() -> new IllegalArgumentException("Meeting not found"));
        meeting.setDate(newDate);
        meeting.setHour(newTime);
        meeting.setStatus(MeetingStatus.REPROGRAMMED);
        return meetingRepository.save(meeting);
    }

    //El metodo cancelMeeting sirve para cancelar una reunion en la base de datos
    //y lo hace cambiando el estado de la reunion a CANCELLED
    public void cancelMeeting(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() -> new IllegalArgumentException("Meeting not found"));
        meeting.setStatus(MeetingStatus.CANCELLED);
        meetingRepository.save(meeting);
    }

    //El metodo markMeetingAsPaid sirve para marcar una reunion como pagada en la base de datos
    public Meeting markMeetingAsPaid(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() -> new IllegalArgumentException("Meeting not found"));
        meeting.setStatus(MeetingStatus.PAID);
        return meetingRepository.save(meeting);
    }
}
