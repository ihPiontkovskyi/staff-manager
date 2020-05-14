package ua.knu.staffmanager.service.mapper;

public interface Mapper<E,D> {
    E mapToEntity(D domain);
    D mapToDomain(E entity);
}
