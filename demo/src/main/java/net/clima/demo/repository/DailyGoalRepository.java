package net.clima.demo.repository;

import net.clima.demo.model.entity.DailyGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyGoalRepository extends JpaRepository<DailyGoal, Long> {

    List<DailyGoal> findDailyGoalByHabit_Id(Long id);
}
