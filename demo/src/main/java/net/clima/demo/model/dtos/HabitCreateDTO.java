package net.clima.demo.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;
import net.clima.demo.model.ENUM.HabitCategory;
import net.clima.demo.model.entity.DailyGoal;
import net.clima.demo.model.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HabitCreateDTO {

    private String name;
    private User user;
    private GoalKind goalKind;
    private HabitCategory habitCategory;
    private String finalDate;
    private String goal;
    private String reference;
    private String color;
}
