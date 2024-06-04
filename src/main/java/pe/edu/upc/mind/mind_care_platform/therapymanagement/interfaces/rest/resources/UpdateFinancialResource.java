package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources;
/**
 * Se encarga de manejar la actualización campos financieros de amount
 */
public record UpdateFinancialResource(Long transactionId, Integer amount) {
}