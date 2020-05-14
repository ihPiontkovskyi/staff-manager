package ua.knu.staffmanager.mapper.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.knu.staffmanager.domain.Crew;
import ua.knu.staffmanager.entity.CrewEntity;
import ua.knu.staffmanager.mapper.CrewMapper;
import ua.knu.staffmanager.mapper.StaffMapper;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CrewMapperImpl implements CrewMapper {

    private final StaffMapper staffMapper;

    @Override
    public CrewEntity mapToEntity(Crew crew) {
        if (isNull(crew)) {
            throw new IllegalArgumentException("Crew is null!");
        }
        return CrewEntity.builder()
                .captain(staffMapper.mapToEntity(crew.getCaptain()))
                .crewMembers(crew.getCrewMembers().stream()
                        .map(staffMapper::mapToEntity)
                        .collect(Collectors.toList()))
                .id(crew.getId())
                .secondPilot(staffMapper.mapToEntity(crew.getSecondPilot()))
                .build();
    }

    @Override
    public Crew mapToDomain(CrewEntity crewEntity) {
        if (isNull(crewEntity)) {
            throw new IllegalArgumentException("Crew entity is null!");
        }
        return Crew.builder()
                .captain(staffMapper.mapToDomain(crewEntity.getCaptain()))
                .crewMembers(crewEntity.getCrewMembers().stream()
                        .map(staffMapper::mapToDomain)
                        .collect(Collectors.toList()))
                .id(crewEntity.getId())
                .secondPilot(staffMapper.mapToDomain(crewEntity.getSecondPilot()))
                .build();
    }
}
