package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.application.internal.commandservices.FinancialCommandServiceImpl;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.commands.CancelTransactionCommand;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources.AddTransactionToFinancialResource;
import pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.transform.AddTransactionToFinancialCommandFromResource;

@RestController
@RequestMapping("/financial")
public class FinancialController {
    private final FinancialCommandServiceImpl financialCommandService;
    private final AddTransactionToFinancialCommandFromResource commandFromResource;

    public FinancialController(FinancialCommandServiceImpl financialCommandService, AddTransactionToFinancialCommandFromResource commandFromResource) {
        this.financialCommandService = financialCommandService;
        this.commandFromResource = commandFromResource;
    }

    @PostMapping("/addTransaction")
    public void addTransaction(@RequestBody AddTransactionToFinancialResource resource) {
        financialCommandService.handle(commandFromResource.toCommand(resource));
    }
    /***Implementar
    @DeleteMapping("/financial/transaction")
    public ResponseEntity<Void> cancelTransaction(@RequestBody CancelTransactionResource resource) {
        CancelTransactionCommand command = cancelTransactionCommandFromResource.toCommand(resource);
        financialCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }*/
}