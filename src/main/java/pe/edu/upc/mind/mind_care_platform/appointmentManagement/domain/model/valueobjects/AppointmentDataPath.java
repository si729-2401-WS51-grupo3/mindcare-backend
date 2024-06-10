package pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.valueobjects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.aggregates.Appointment;
import pe.edu.upc.mind.mind_care_platform.appointmentManagement.domain.model.entities.AppointmentData;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class AppointmentDataPath {
    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private List<AppointmentData> appointmentDataItems;

    public AppointmentDataPath() {
        this.appointmentDataItems = new ArrayList<>();
    }
    public void addItem(Appointment appointment, Long nextItem) {
        System.out.println("Adding item to appointment data path");
        AppointmentData appointmentData = new AppointmentData(appointment, nextItem);
        System.out.println("Appointment data Id " + appointmentData.getId());
        appointmentDataItems.add(appointmentData);
    }
    public AppointmentData getAppointmentDataWithAppointmentId(Long appointmentId) {
        return appointmentDataItems.stream().filter(appointmentData -> appointmentData.getAppointment().getId().equals(appointmentId)).findFirst().orElse(null);
    }
}
