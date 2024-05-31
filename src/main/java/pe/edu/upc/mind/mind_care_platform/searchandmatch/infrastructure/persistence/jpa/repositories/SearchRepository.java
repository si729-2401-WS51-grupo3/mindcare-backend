package pe.edu.upc.mind.mind_care_platform.searchandmatch.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.mind.mind_care_platform.searchandmatch.domain.model.valueobjects.Psychologist;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Psychologist, Long> {
    List<Psychologist> findBySpeciality(String speciality);
    boolean existsBySpeciality(String speciality);
    boolean existsBySpecialityAndIdIsNot(String speciality, Long id);
}