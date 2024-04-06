package net.clima.demo.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.clima.demo.model.entity.GoalKindsValues.BooleanType;
import net.clima.demo.model.entity.GoalKindsValues.Quantity;
import net.clima.demo.model.entity.GoalKindsValues.Time;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateHabit {

    private Long dailyId;
    private Quantity quantity;
    private Time time;
    private BooleanType booleanS;

}
