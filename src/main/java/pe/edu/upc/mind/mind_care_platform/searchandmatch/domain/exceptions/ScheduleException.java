package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.exceptions;

public class ScheduleException extends RuntimeException {
  public ScheduleException(Long id) {
    super("Schedule with id " + id + " already exists");
  }
}