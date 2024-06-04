package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllFinancialTransactionByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllFinancialTransactionQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetFinancialTransactionByIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.services.FinancialCommandService;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.services.FinancialQueryService;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.CreateFinancialResource;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.FinancialResource;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.UpdateFinancialResource;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform.CreateFinancialCommandFromResourceAssembler;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform.FinancialResourceFromEntityAssembler;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform.UpdateFinancialCommandFromResourceAssembler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/financials", produces = APPLICATION_JSON_VALUE)
public class FinancialsController {
    private final FinancialCommandService financialCommandService;
    private final FinancialQueryService financialQueryService;

    public FinancialsController(FinancialCommandService financialCommandService, FinancialQueryService financialQueryService) {
        this.financialCommandService = financialCommandService;
        this.financialQueryService = financialQueryService;
    }

    @PostMapping
    public ResponseEntity<FinancialResource> createFinancial(@Valid @RequestBody CreateFinancialResource createFinancialResource) {
        if (createFinancialResource.pyschologistId() == null || createFinancialResource.patientId() == null) {
            return ResponseEntity.badRequest().build();
        }
        var createFinancialCommand = CreateFinancialCommandFromResourceAssembler.toCommandFromResource(createFinancialResource);
        var financialId = financialCommandService.handle(createFinancialCommand);

        if (financialId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getFinancialByIdQuery = new GetFinancialTransactionByIdQuery(financialId);
        var financial = financialQueryService.handle(getFinancialByIdQuery);

        if (financial.isEmpty())
            return ResponseEntity.badRequest().build();
        var financialResource = FinancialResourceFromEntityAssembler.toResourceFromEntity(financial.get());
        return new ResponseEntity<>(financialResource, HttpStatus.CREATED);
    }

    @GetMapping("/{financialId}")
    public ResponseEntity<FinancialResource> getFinancialById(@PathVariable Long financialId) {
        var getFinancialByIdQuery = new GetFinancialTransactionByIdQuery(financialId);
        var financial = financialQueryService.handle(getFinancialByIdQuery);

        if (financial.isEmpty())
            return ResponseEntity.badRequest().build();
        var financialResource = FinancialResourceFromEntityAssembler.toResourceFromEntity(financial.get());
        return ResponseEntity.ok(financialResource);
    }
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<FinancialResource>> getFinancialsByPatientId(@PathVariable String patientId) {
        var getAllFinancialsByPatientIdQuery = new GetAllFinancialTransactionByPatientIdQuery(patientId);
        var financials = financialQueryService.handle(getAllFinancialsByPatientIdQuery);
        var financialResources = financials.stream()
                .map(FinancialResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(financialResources);
    }

    @GetMapping
    public ResponseEntity<List<Object>> getAllFinancials() {
        var getAllFinancialsQuery = new GetAllFinancialTransactionQuery();
        var financials = financialQueryService.handle(getAllFinancialsQuery);
        var financialResources = financials.stream().map(FinancialResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(Collections.singletonList(financialResources));
    }

    @PutMapping("/{financialId}")
    public ResponseEntity<FinancialResource> updateFinancial(@PathVariable Long financialId, @RequestBody UpdateFinancialResource updateFinancialResource) {
        var updateFinancialCommand = UpdateFinancialCommandFromResourceAssembler.toCommandFromResource(updateFinancialResource);
        var updatedFinancial = financialCommandService.handle(updateFinancialCommand);

        if (updatedFinancial.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var financialResource = FinancialResourceFromEntityAssembler.toResourceFromEntity(updatedFinancial.get());
        return ResponseEntity.ok(financialResource);
    }
}