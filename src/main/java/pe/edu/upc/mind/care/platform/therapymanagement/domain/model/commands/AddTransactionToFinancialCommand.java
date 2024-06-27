package pe.edu.upc.mind.care.platform.therapymanagement.domain.model.commands;
/**
 *Agrega una transaccion a un financial (lista que almacena las transacciones de un paciente)
 */
public record AddTransactionToFinancialCommand(Long patientId, Long pyschologistId,Long reservationId, Integer amount) {

    public int getAmount() {
        return amount;
    }

    public Long reservationId() {
        return reservationId;
    }
}