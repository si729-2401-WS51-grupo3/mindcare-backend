package pe.edu.upc.mind.mind_care_platform.shared.interfaces.rest.transform;

public interface DtoMapper<D, E> {
    E toEntity(D dto);
    D toDto(E entity);
}