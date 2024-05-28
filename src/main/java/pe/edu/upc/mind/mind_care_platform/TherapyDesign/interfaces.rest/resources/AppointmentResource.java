package pe.edu.upc.mind.mind_care_platform.TherapyDesign.interfaces.rest.resources;

import pe.edu.upc.mind.mind_care_platform.TherapyDesign.domain.model.entities.Appointment;
import pe.edu.upc.mind.mind_care_platform.TherapyDesign.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/appointments")
public class AppointmentResource {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
