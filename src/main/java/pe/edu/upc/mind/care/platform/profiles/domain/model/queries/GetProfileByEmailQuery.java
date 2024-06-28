package pe.edu.upc.mind.care.platform.profiles.domain.model.queries;

import pe.edu.upc.mind.care.platform.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}