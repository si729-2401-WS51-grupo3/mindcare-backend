package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.application.internal.commandservices.FinancialCommandServiceImpl;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.application.internal.queryservices.FinancialQueryServiceImpl;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.aggregates.Financial;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CancelTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.entities.Transaction;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllTransactionsByFinancialId;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetAllTransactionsByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetFinancialIdByPatientIdQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries.GetPatientFinancialQuery;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.AddTransactionToFinancialResource;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.CancelTransactionCommandResource;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform.AddTransactionToFinancialCommandFromResource;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform.CancelTransactionCommandFromResource;

import java.util.List;
import java.util.Map;

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

    /**
     *  Recupera información sobre transacciones vinculadas gracias al financial_id
     */
    @GetMapping("/getFinancial/{financialId}")
    public ResponseEntity<Financial> getFinancial(@PathVariable Long financialId) {
        return financialQueryService.handle(new GetPatientFinancialQuery(financialId))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     *  Recupera la informacion del id de una finanza gracias al id de un paciente
     */
    @GetMapping("/getFinancialByPatientId/{patientId}")
    public ResponseEntity<List<Map<String, Object>>> getFinancialByPatientId(@PathVariable Long patientId) {
        List<Map<String, Object>> financials = financialQueryService.handle(new GetFinancialIdByPatientIdQuery(patientId));
        if (!financials.isEmpty()) {
            return ResponseEntity.ok(financials);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  Recupera la informacion de las transacciones gracias al id de un paciente
     */
    @GetMapping("/getAllTransactionsByPatientId/{patientId}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByPatientId(@PathVariable Long patientId) {
        List<Transaction> transactions = financialQueryService.handle(new GetAllTransactionsByPatientIdQuery(patientId));
        if (!transactions.isEmpty()) {
            return ResponseEntity.ok(transactions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     *  Recupera la informacion de las transacciones gracias al id de una finanza
     */
    @GetMapping("/getAllTransactionsByFinancialId/{financialId}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByFinancialId(@PathVariable Long financialId) {
        List<Transaction> transactions = financialQueryService.handle(new GetAllTransactionsByFinancialId(financialId));
        if (!transactions.isEmpty()) {
            return ResponseEntity.ok(transactions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}