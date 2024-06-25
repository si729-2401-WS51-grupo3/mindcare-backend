package pe.edu.upc.mind.mind_care_platform.profiles.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.commands.UpdatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects.EmailAddress;
import pe.edu.upc.mind.mind_care_platform.profiles.domain.services.PsychologistCommandService;
import pe.edu.upc.mind.mind_care_platform.profiles.infrastructure.persistence.jpa.repositories.PsychologistRepository;

import java.util.Optional;

@Service
public class PsychologistCommandServiceImpl implements PsychologistCommandService {
    private final PsychologistRepository psychologistRepository;
    public PsychologistCommandServiceImpl(PsychologistRepository psychologistRepository) {
        this.psychologistRepository = psychologistRepository;
    }

    @Override
    public Optional<Psychologist> handle(CreatePsychologistCommand command) {
        Psychologist psychologist = new Psychologist(
                command.firstName(),
                command.lastName(),
                command.email(),
                command.phone(),
                command.photoUrl(),
                command.occupation(),
                command.description(),
                command.gender(),
                command.birthDate(),
                command.scheduleId()
        );

        try {
            psychologist = psychologistRepository.save(psychologist);
            return Optional.of(psychologist);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Psychologist> handle(UpdatePsychologistCommand command) {
        Optional<Psychologist> optionalPsychologist = psychologistRepository.findByEmail_Address(command.email());

        if (optionalPsychologist.isPresent()) {
            Psychologist psychologist = optionalPsychologist.get();
            psychologist.updateEmail(command.email());
            psychologist.updatePhone(command.phone());

            try {
                psychologist = psychologistRepository.save(psychologist);
                return Optional.of(psychologist);
            } catch (Exception e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
}
