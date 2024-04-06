package net.clima.demo.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;
import net.clima.demo.model.dtos.UpdateHabit;
import net.clima.demo.model.entity.DailyGoal;
import net.clima.demo.model.entity.GoalKindsValues.BooleanType;
import net.clima.demo.model.entity.GoalKindsValues.Quantity;
import net.clima.demo.model.entity.GoalKindsValues.Time;
import net.clima.demo.model.entity.Habits;
import net.clima.demo.repository.DailyGoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DailyGoalService {

    private DailyGoalRepository dailyGoalRepository;
    private HabitService habitService;
    public DailyGoal save(DailyGoal dailyGoal){
        dailyGoal.setDay(LocalDateTime.now());
        return dailyGoalRepository.save(dailyGoal);
    }

    public DailyGoal findById(Long id){
        return dailyGoalRepository.findById(id).get();
    }

    public DailyGoal update(UpdateHabit updateHabit){
        System.out.println(updateHabit);
        DailyGoal dailyGoal = findById(updateHabit.getDailyId());
        if(dailyGoal.getQuantity() != null) {
            if(updateHabit.getQuantity() !=null) {
                Quantity quantity = dailyGoal.getQuantity();
                quantity.setCurrentStatus(updateHabit.getQuantity().getCurrentStatus());
                if (dailyGoal.getQuantity().getReference() != null) {
                    quantity.setReference(updateHabit.getQuantity().getReference());
                }
            }
        }else if(dailyGoal.getBooleanS() != null){
            if(updateHabit.getBooleanS() != null){
                BooleanType booleanType = dailyGoal.getBooleanS();
                booleanType.setCurrentStatus(updateHabit.getBooleanS().getCurrentStatus());
            }
        }else {
            if(dailyGoal.getTime().getTimeReference() != null){
                if(updateHabit.getTime() != null) {
                    Time time = dailyGoal.getTime();
                    time.setCurrentStatus(updateHabit.getTime().getCurrentStatus());
                }
            }
        }
        setAsDone(dailyGoal);
        return dailyGoalRepository.save(dailyGoal);
    }

    public void setAsDone(DailyGoal dailyGoal){
        if(dailyGoal.getQuantity() != null){
            if(dailyGoal.getQuantity().getCurrentStatus().equals(dailyGoal.getQuantity().getGoal())) {
                dailyGoal.setDone(true);
            }
        }else if(dailyGoal.getTime() != null){
            if(dailyGoal.getTime().getCurrentStatus() == dailyGoal.getTime().getGoal()) {
                dailyGoal.setDone(true);
            }
        }
    }

    public List<Habits> getHabitsDoneInADay(Long userId){
        List<Habits> habits = habitService.findByUser(userId);
        List<Habits> completedHabits = new ArrayList<>();

        for(Habits habit1 : habits){
            for(DailyGoal dailyGoal : habit1.getDailyGoalList()){
                if(dailyGoal.getDay().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()){
                    completedHabits.add(habit1);
                }
            }
        }
        return completedHabits;
    }

}
