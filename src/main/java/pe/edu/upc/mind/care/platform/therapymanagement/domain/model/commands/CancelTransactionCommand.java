package pe.edu.upc.mind.care.platform.therapymanagement.domain.model.commands;
/**
 * Elimina una transaccion de la lista financial de un paciente
 */
public record CancelTransactionCommand(Long patientId, Long transactionId ) {

        public Long getTransactionId() {
            return transactionId;
        }

        public Long getPatientId() {
            return patientId;
        }
}
