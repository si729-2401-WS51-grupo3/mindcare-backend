package pe.edu.upc.mind.mind_care_platform.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.UpdatePsychologistCommand;

import java.util.Optional;
public interface PsychologistCommandService {
    Optional<Psychologist> handle(CreatePsychologistCommand command);
    Optional<Psychologist> handle(UpdatePsychologistCommand command);
}