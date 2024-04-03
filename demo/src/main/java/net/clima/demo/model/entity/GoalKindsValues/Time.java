package net.clima.demo.model.entity.GoalKindsValues;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;
import net.clima.demo.model.ENUM.TimeEnum;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Time{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime goal;
    private LocalDateTime currentStatus;
    @Enumerated(value = EnumType.STRING)
    private TimeEnum timeReference;

}
