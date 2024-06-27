package pe.edu.upc.mind.care.platform.searchandmatch.domain.exceptions;

public class PsychologistNotFoundException extends RuntimeException {
  public PsychologistNotFoundException(Long id) {
    super("Schedule with id " + id + " already exists");
  }

}