package net.clima.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;
import net.clima.demo.model.ENUM.HabitCategory;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
public class Habits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private User user;
    @Enumerated(value = EnumType.STRING)
    private GoalKind goalKind;
    @Enumerated(value = EnumType.STRING)
    private HabitCategory habitCategory;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DailyGoal> dailyGoalList;
    private LocalDateTime finalDate;
    private String color;
}
