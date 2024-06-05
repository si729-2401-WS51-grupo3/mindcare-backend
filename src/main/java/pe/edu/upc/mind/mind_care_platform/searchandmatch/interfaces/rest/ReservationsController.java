package pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetAllReservationsQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationByIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByPatientIdAndReservationDate;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.queries.GetReservationsByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ReservationCommandService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.ReservationQueryService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.CreateReservationResource;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.resources.ReservationResource;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform.CreateReservationCommandFromResourceAssembler;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.interfaces.rest.transform.ReservationResourceFromEntityAssembler;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/reservations", produces = APPLICATION_JSON_VALUE)
public class ReservationsController {
    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;

    public ReservationsController(ReservationCommandService reservationCommandService, ReservationQueryService reservationQueryService) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService = reservationQueryService;
    }

    @PostMapping
    public ResponseEntity<ReservationResource> createReservation(@RequestBody CreateReservationResource resource) {
        var createReservationCommand = CreateReservationCommandFromResourceAssembler.toCommandFromResource(resource);
        var reservationId = reservationCommandService.handle(createReservationCommand);

        if (reservationId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var reservation = reservationQueryService.handle(getReservationByIdQuery);

        if (reservation.isEmpty())
            return ResponseEntity.badRequest().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return new ResponseEntity<>(reservationResource, HttpStatus.CREATED);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResource> getReservationById(@PathVariable Long reservationId) {
        var getReservationByIdQuery = new GetReservationByIdQuery(reservationId);
        var reservation = reservationQueryService.handle(getReservationByIdQuery);

        if (reservation.isEmpty())
            return ResponseEntity.badRequest().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(reservation.get());
        return ResponseEntity.ok(reservationResource);
    }
    @GetMapping
    public ResponseEntity<List<ReservationResource>> getAllReservations() {
        var getAllReservationsQuery = new GetAllReservationsQuery();
        var reservations = reservationQueryService.handle(getAllReservationsQuery);
        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservationResources);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ReservationResource>> getReservationsByPatientId(@PathVariable Long patientId) {
        var getReservationsByPatientIdQuery = new GetReservationsByPatientIdQuery(patientId);
        var reservations = reservationQueryService.handle(getReservationsByPatientIdQuery);

        if (reservations.isEmpty())
            return ResponseEntity.notFound().build();

        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(reservationResources);
    }
    @GetMapping("/patient/{patientId}/date/{reservationDate}")
    public ResponseEntity<List<ReservationResource>> getReservationsByPatientIdAndReservationDate(@PathVariable Long patientId, @PathVariable String reservationDate) {
        var getReservationsByPatientIdAndReservationDate = new GetReservationsByPatientIdAndReservationDate(patientId, reservationDate);
        var reservations = reservationQueryService.handle(getReservationsByPatientIdAndReservationDate);

        if (reservations.isEmpty())
            return ResponseEntity.notFound().build();

        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(reservationResources);
    }
}
