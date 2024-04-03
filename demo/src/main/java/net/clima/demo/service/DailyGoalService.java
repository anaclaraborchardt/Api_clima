package net.clima.demo.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;
import net.clima.demo.model.dtos.UpdateHabit;
import net.clima.demo.model.entity.DailyGoal;
import net.clima.demo.model.entity.GoalKindsValues.Quantity;
import net.clima.demo.model.entity.GoalKindsValues.Time;
import net.clima.demo.model.entity.Habits;
import net.clima.demo.repository.DailyGoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
            Quantity quantity = dailyGoal.getQuantity();
            quantity.setCurrentStatus(updateHabit.getQuantity().getCurrentStatus());
            if(dailyGoal.getQuantity().getReference() != null){
               quantity.setReference(updateHabit.getQuantity().getReference());
            }
        }else if(dailyGoal.getBooleanS() != null){
//            dailyGoal.getBooleanS().setCurrentStatus(updateHabit.getBooleanS().);
        }else {
            Time time = dailyGoal.getTime();
            time.setCurrentStatus(updateHabit.getTime().getCurrentStatus());
            if(dailyGoal.getTime().getTimeReference() != null){
                time.setTimeReference(updateHabit.getTime().getTimeReference());
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

}
