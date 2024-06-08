package pe.edu.upc.mind.mind_care_platform.searchandmatch.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.DeletePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.commands.UpdatePsychologistCommand;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.entities.Psychologist;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.services.PsychologistCommandService;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories.PsychologistRepository;

import java.util.Optional;

@Service
public class PsychologistCommandServiceImpl implements PsychologistCommandService {
    private final PsychologistRepository psychologistRepository;
    public PsychologistCommandServiceImpl(PsychologistRepository psychologistRepository) {
        this.psychologistRepository = psychologistRepository;
    }
    @Override
    public Long handle(CreatePsychologistCommand command) {
        var psychologist=new Psychologist(command);
        try{
            psychologistRepository.save(psychologist);
        }
        catch (Exception e){
            throw new RuntimeException("Error al guardar el psicólogo " + e.getMessage());
        }
        return psychologist.getId();
    }

    @Override
    public Optional<Psychologist> handle (UpdatePsychologistCommand command){
        var result = psychologistRepository.findById(command.id());
        if(result.isEmpty())
            throw new IllegalArgumentException("El psicólogo no existe");
        var psychologistToUpdate = result.get();
        try{
            var updatedPsychologist = psychologistRepository
                    .save(psychologistToUpdate.updateInformation(
                            command.name(),
                            command.worked_hours(),
                            command.started_hour())
                    );
            return Optional.of(updatedPsychologist);
        }
        catch (Exception e){
            throw new RuntimeException("Error al actualizar el psicólogo " + e.getMessage());
        }
    }

    @Override
    public void handle(DeletePsychologistCommand command) {
        if(!psychologistRepository.existsById(command.id()))
            throw new IllegalArgumentException("El psicólogo no existe");
        try{
            psychologistRepository.deleteById(command.id());
        }
        catch (Exception e){
            throw new RuntimeException("Error al eliminar el psicólogo " + e.getMessage());
        }
    }
}
