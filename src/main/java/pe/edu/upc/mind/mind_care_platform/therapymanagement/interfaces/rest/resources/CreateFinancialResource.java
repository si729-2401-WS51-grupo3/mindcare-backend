package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record CreateFinancialResource(@NotNull String patientId, @NotNull String pyschologistId) {
}
