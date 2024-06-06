package pe.edu.upc.mind.mind_care_platform.therapymanagement.domain.model.queries;

public record GetAllTransactionsByPatientIdQuery(Long patientId) {
    public GetAllTransactionsByPatientIdQuery {
        if (patientId == null) {
            throw new IllegalArgumentException("Patient id must not be null");
        }
    }
    public GetAllTransactionsByPatientIdQuery() {
        this(0L);
    }
}
