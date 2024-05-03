package net.clima.demo.service;

import lombok.AllArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;
import net.clima.demo.model.dtos.HabitCreateDTO;
import net.clima.demo.model.dtos.UpdateHabitInfo;
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

    public Habits findOne(Long habitId, Long userId){
        System.out.println(habitRepository.findByIdAndUser_Id(habitId, userId));
        return habitRepository.findByIdAndUser_Id(habitId, userId);
    }

    public List<Habits> findByUser(Long id){
        return habitRepository.findAllByUser_Id(id);
    }

    public Habits findOne(Long habitId){
        return habitRepository.findById(habitId).get();
    }

    public void deleteHabit(Long id){
        habitRepository.deleteById(id);
    }

    public void changeKind(GoalKind goalKind, Long id){
        Habits habit = findOne(id);
        habit.setDailyGoalList(null);
        habit.setGoalKind(goalKind);
        habitRepository.save(habit);
    }

    public Habits updateHabit(UpdateHabitInfo updateHabitInfo){
        System.out.println("ut " + updateHabitInfo);
        Habits habits = new Habits();
        if(updateHabitInfo.getGoalKind() !=null) {
            changeKind(updateHabitInfo.getGoalKind(), updateHabitInfo.getId());
        }
        BeanUtils.copyProperties(updateHabitInfo, habits);
        habitRepository.save(habits);
        return habits;
    }
}
