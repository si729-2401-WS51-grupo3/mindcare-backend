package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.application.internal.commandservices.FinancialCommandServiceImpl;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.application.internal.queryservices.FinancialQueryServiceImpl;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CancelTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetPatientFinancialQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.AddTransactionToFinancialResource;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.CancelTransactionCommandResource;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform.AddTransactionToFinancialCommandFromResource;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform.CancelTransactionCommandFromResource;

@RestController
@RequestMapping("/financial")
public class FinancialController {
    private final FinancialCommandServiceImpl financialCommandService;
    private final FinancialQueryServiceImpl financialQueryService;
    private final AddTransactionToFinancialCommandFromResource addCommandFromResource;
    private final CancelTransactionCommandFromResource cancelCommandFromResource;

    public FinancialController(FinancialCommandServiceImpl financialCommandService, FinancialQueryServiceImpl financialQueryService, AddTransactionToFinancialCommandFromResource addCommandFromResource, CancelTransactionCommandFromResource cancelCommandFromResource) {
        this.financialCommandService = financialCommandService;
        this.financialQueryService = financialQueryService;
        this.addCommandFromResource = addCommandFromResource;
        this.cancelCommandFromResource = cancelCommandFromResource;
    }

    @PostMapping("/addTransaction")
    public ResponseEntity<?> addTransaction(@RequestBody AddTransactionToFinancialResource resource) {
        if (resource.pyschologistId() == null) {
            return ResponseEntity.badRequest().body("PyschologistId cannot be null");
        }
        financialCommandService.handle(addCommandFromResource.toCommandFromResource(resource));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cancelTransaction")
    public ResponseEntity<?> cancelTransaction(@RequestBody CancelTransactionCommandResource resource) {
        CancelTransactionCommand command = cancelCommandFromResource.toCommandFromResource(resource);
        financialCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getFinancial/{financialId}")
    public ResponseEntity<Financial> getFinancial(@PathVariable Long financialId) {
        return financialQueryService.handle(new GetPatientFinancialQuery(financialId))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}