package net.clima.demo.model.dtos;

import jakarta.persistence.*;
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
public class UpdateHabitInfo {

    private Long id;
    private String name;
    private HabitCategory habitCategory;
    private String finalDate;
    private String color;
    private String reference;
    private GoalKind goalKind;

}
