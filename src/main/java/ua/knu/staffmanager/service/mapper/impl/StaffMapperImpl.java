package ua.knu.staffmanager.service.mapper.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.knu.staffmanager.domain.Role;
import ua.knu.staffmanager.domain.Staff;
import ua.knu.staffmanager.entity.RoleEntity;
import ua.knu.staffmanager.entity.StaffEntity;
import ua.knu.staffmanager.service.mapper.AirportMapper;
import ua.knu.staffmanager.service.mapper.StaffMapper;

import static java.util.Objects.isNull;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StaffMapperImpl implements StaffMapper {

    private final AirportMapper airportMapper;

    @Override
    public StaffEntity mapToEntity(Staff staff) {
        if (isNull(staff)) {
            throw new IllegalArgumentException("Staff is null!");
        }
        return StaffEntity.builder()
                .fullName(staff.getFullName())
                .id(staff.getId())
                .identifier(staff.getIdentifier())
                .location(airportMapper.mapToEntity(staff.getLocation()))
                .password(staff.getPassword())
                .roleEntity(RoleEntity.valueOf(staff.getRole().name()))
                .build();
    }

    @Override
    public Staff mapToDomain(StaffEntity entity) {
        if (isNull(entity)) {
            throw new IllegalArgumentException("Staff entity is null!");
        }
        return Staff.builder()
                .fullName(entity.getFullName())
                .id(entity.getId())
                .identifier(entity.getIdentifier())
                .location(airportMapper.mapToDomain(entity.getLocation()))
                .password(entity.getPassword())
                .role(Role.valueOf(entity.getRoleEntity().name()))
                .build();
    }
}
