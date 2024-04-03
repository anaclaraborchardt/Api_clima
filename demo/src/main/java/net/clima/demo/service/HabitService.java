package net.clima.demo.service;

import lombok.AllArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;
import net.clima.demo.model.dtos.HabitCreateDTO;
import net.clima.demo.model.dtos.UpdateHabitKind;
import net.clima.demo.model.entity.DailyGoal;
import net.clima.demo.model.entity.GoalKindsValues.Quantity;
import net.clima.demo.model.entity.Habits;
import net.clima.demo.model.entity.User;
import net.clima.demo.repository.HabitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HabitService {

    private HabitRepository habitRepository;

    public Habits save(HabitCreateDTO habitCreateDTO){
        Habits habits = new Habits();
        BeanUtils.copyProperties(habitCreateDTO, habits);
        return habitRepository.save(habits);
    }

    public Habits findOne(Long id){
        return habitRepository.findById(id).get();
    }

    public List<Habits> findByUser(Long id){
        return habitRepository.findAllByUser_Id(id);
    }

    public void deleteHabit(Long id){
        habitRepository.deleteById(id);
    }

    public void changeKind(UpdateHabitKind updateHabitKind){
        Habits habit = findOne(updateHabitKind.getHabitId());
        habit.setDailyGoalList(null);
        habit.setGoalKind(updateHabitKind.getGoalKind());
        habitRepository.save(habit);
    }
}
