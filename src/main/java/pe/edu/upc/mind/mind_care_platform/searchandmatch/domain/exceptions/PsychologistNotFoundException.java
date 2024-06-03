package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.exceptions;

public class PsychologistNotFoundException extends RuntimeException {
  public PsychologistNotFoundException(Long id) {
    super("Psychologist with id " + id + " not found");
  }
}