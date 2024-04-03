package net.clima.demo.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateHabitKind {

    private Long habitId;
    private GoalKind goalKind;
}
