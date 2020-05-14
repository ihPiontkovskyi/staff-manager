package ua.knu.staffmanager.mapper;

public interface Mapper<E,D> {
    E mapToEntity(D domain);
    D mapToDomain(E entity);
}
