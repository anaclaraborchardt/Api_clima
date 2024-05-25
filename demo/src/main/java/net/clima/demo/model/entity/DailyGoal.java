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
    @ManyToOne(cascade = CascadeType.ALL)
    private Quantity quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    private Time time;
    @ManyToOne(cascade = CascadeType.ALL)
    private BooleanType booleanS;
    private boolean done;

    public DailyGoal(Habits habit, Quantity quantity) {
        this.habit = habit;
        this.quantity = quantity;
    }

    public DailyGoal(Habits habit, BooleanType booleanType) {
        this.habit = habit;
        this.booleanS = booleanType;
    }

    public DailyGoal(Habits habit, Time time) {
        this.habit = habit;
        this.time = time;
    }


}
