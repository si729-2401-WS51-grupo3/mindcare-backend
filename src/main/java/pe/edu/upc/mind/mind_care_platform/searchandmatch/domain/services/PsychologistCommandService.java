package pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.DeletePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.UpdatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Psychologist;

import java.util.Optional;

public interface PsychologistCommandService {
    Long handle(CreatePsychologistCommand command);
    Optional<Psychologist> handle(UpdatePsychologistCommand command);
    void handle(DeletePsychologistCommand command);
}