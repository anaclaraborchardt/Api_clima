package net.clima.demo.model.dtos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.clima.demo.model.entity.DailyGoal;
import net.clima.demo.model.entity.GoalKindsValues.Boolean;
import net.clima.demo.model.entity.GoalKindsValues.Quantity;
import net.clima.demo.model.entity.GoalKindsValues.Time;
import net.clima.demo.model.entity.Habits;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateHabit {

    private Long dailyId;
    private Quantity quantity;
    private Time time;
    private Boolean booleanS;

}
