package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.exceptions;

public class ScheduleNotFoundException extends RuntimeException
{
    public ScheduleNotFoundException(Long id)
    {
        super("Schedule with id " + id + " not found");
    }
}
