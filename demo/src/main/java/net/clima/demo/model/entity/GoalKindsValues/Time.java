package net.clima.demo.model.entity.GoalKindsValues;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Integer goal;
    private Integer currentStatus;
    @Enumerated(value = EnumType.STRING)
    private TimeEnum timeReference;

    public Time(Integer goal) {
        this.goal = goal;
        this.currentStatus = 0;
    }
}
