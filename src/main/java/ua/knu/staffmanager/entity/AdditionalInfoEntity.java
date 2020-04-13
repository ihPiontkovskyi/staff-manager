package ua.knu.staffmanager.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@Entity
@Table(name = "additional_info")
public class AdditionalInfoEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission")
    private List<BoardTypeEntity> permission;
}
