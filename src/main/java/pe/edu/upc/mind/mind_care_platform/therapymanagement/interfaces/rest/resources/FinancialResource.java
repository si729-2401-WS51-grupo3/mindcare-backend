package pe.edu.upc.mind.mind_care_platform.therapymanagement.interfaces.rest.resources;
/**
 *Asumo que los identificadores de pacientes y psicólogos son validos,
 * ya que fueron generados y validados en otro Bounded Context.
 */
public record FinancialResource(Long id,String patientId,String pyschologistId) {
}
