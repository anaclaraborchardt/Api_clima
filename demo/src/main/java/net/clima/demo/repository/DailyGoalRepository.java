package net.clima.demo.repository;

import net.clima.demo.model.entity.DailyGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyGoalRepository extends JpaRepository<DailyGoal, Long> {
}
