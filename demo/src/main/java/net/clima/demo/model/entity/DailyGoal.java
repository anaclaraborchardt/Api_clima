package net.clima.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.clima.demo.model.entity.GoalKindsValues.BooleanType;
import net.clima.demo.model.entity.GoalKindsValues.Quantity;
import net.clima.demo.model.entity.GoalKindsValues.Time;


import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DailyGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime day;
    @ManyToOne
    private Habits habit;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Quantity quantity;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Time time;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private BooleanType booleanS;
    private boolean done;

}
