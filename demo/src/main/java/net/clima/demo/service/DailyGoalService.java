package net.clima.demo.service;

import lombok.AllArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;
import net.clima.demo.model.dtos.HabitCreateDTO;
import net.clima.demo.model.dtos.UpdateDailyGoal;
import net.clima.demo.model.entity.DailyGoal;
import net.clima.demo.model.entity.GoalKindsValues.BooleanType;
import net.clima.demo.model.entity.GoalKindsValues.Quantity;
import net.clima.demo.model.entity.GoalKindsValues.Time;
import net.clima.demo.model.entity.Habits;
import net.clima.demo.repository.BooleanTypeRepository;
import net.clima.demo.repository.DailyGoalRepository;
import net.clima.demo.repository.HabitRepository;
import net.clima.demo.repository.QuantityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DailyGoalService {

    private DailyGoalRepository dailyGoalRepository;
    private HabitRepository habitRepository;
    private final QuantityRepository quantityRepository;
    private final BooleanTypeRepository booleanTypeRepository;
    public void save(DailyGoal dailyGoal, GoalKind kind, HabitCreateDTO habitCreateDTO) {
        Habits habit = habitRepository.findById(dailyGoal.getHabit().getId()).get();

        LocalDateTime startDate = LocalDateTime.now();

        for (LocalDateTime date = startDate; !date.isAfter(habit.getFinalDate()); date = date.plusDays(1)) {
            DailyGoal goalForDay = new DailyGoal();
            goalForDay.setHabit(habit);
            goalForDay.setDay(date);

            if(kind == GoalKind.quantidade){
                Integer goal = Integer.valueOf(habitCreateDTO.getGoal());
                Quantity quantity = quantityRepository.save(new Quantity(goal));
                goalForDay.setQuantity(quantity);
            } else if(kind == GoalKind.booleano){
                boolean goal = Boolean.parseBoolean(habitCreateDTO.getGoal());
                BooleanType bool = booleanTypeRepository.save(new BooleanType(goal));
                goalForDay.setBooleanS(bool);
            } else if(kind == GoalKind.tempo){
                goalForDay.setTime(dailyGoal.getTime());
            }
            dailyGoalRepository.save(goalForDay);
        }
    }

    public DailyGoal findById(Long id){
        return dailyGoalRepository.findById(id).get();
    }

    public DailyGoal update(UpdateDailyGoal updateHabit){
        System.out.println(updateHabit);
        DailyGoal dailyGoal = findById(updateHabit.getDailyId());
        if(dailyGoal.getQuantity() != null) {
            if(updateHabit.getQuantity() !=null) {
                Quantity quantity = dailyGoal.getQuantity();
                quantity.setCurrentStatus(updateHabit.getQuantity().getCurrentStatus());
            }
        }else if(dailyGoal.getBooleanS() != null){
//            if(updateHabit.getBooleanS() != null){
//                BooleanType booleanType = dailyGoal.getBooleanS();
//                booleanType.setCurrentStatus(updateHabit.getBooleanS());
//            }
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
        List<Habits> habits = habitRepository.findAllByUser_Id(userId);
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
