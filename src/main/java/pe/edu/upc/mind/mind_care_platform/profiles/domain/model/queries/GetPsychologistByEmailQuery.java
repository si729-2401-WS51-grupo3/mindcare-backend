package pe.edu.upc.mind.mind_care_platform.profiles.domain.model.queries;

import pe.edu.upc.mind.mind_care_platform.profiles.domain.model.valueobjects.EmailAddress;

public record GetPsychologistByEmailQuery(EmailAddress emailAddress) {
}