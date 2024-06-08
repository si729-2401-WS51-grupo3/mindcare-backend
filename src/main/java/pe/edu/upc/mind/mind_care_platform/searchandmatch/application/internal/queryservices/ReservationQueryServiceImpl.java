package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.ReservationRepository;

@Service
public class ReservationQueryServiceImpl {
    private final ReservationRepository reservationRepository;
    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

}
