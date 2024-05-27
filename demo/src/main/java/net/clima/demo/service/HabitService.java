package net.clima.demo.service;

import lombok.AllArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;
import net.clima.demo.model.dtos.HabitCreateDTO;
import net.clima.demo.model.dtos.UpdateHabitInfo;
import net.clima.demo.model.entity.DailyGoal;
import net.clima.demo.model.entity.GoalKindsValues.BooleanType;
import net.clima.demo.model.entity.GoalKindsValues.Quantity;
import net.clima.demo.model.entity.GoalKindsValues.Time;
import net.clima.demo.model.entity.Habits;
import net.clima.demo.repository.BooleanTypeRepository;
import net.clima.demo.repository.HabitRepository;
import net.clima.demo.repository.QuantityRepository;
import net.clima.demo.repository.TimeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class HabitService {

    private HabitRepository habitRepository;
    private DailyGoalService dailyGoalService;

    public Habits save(HabitCreateDTO habitCreateDTO){
        Habits habits = new Habits();
        BeanUtils.copyProperties(habitCreateDTO, habits);
        habitRepository.save(habits);
        try {
            if(habits.getGoalKind() == GoalKind.quantidade){
                dailyGoalService.save(new DailyGoal(habits), GoalKind.quantidade, habitCreateDTO);
            } else if(habits.getGoalKind() == GoalKind.booleano){
                dailyGoalService.save(new DailyGoal(habits), GoalKind.booleano, habitCreateDTO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return habits;
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

    public Habits updateHabit(UpdateHabitInfo updateHabitInfo) throws ParseException {
        System.out.println("ut " + updateHabitInfo);
        Habits habits = findOne(updateHabitInfo.getId());
        if(updateHabitInfo.getGoalKind() !=null) {
            changeKind(updateHabitInfo.getGoalKind(), updateHabitInfo.getId());
        }
        BeanUtils.copyProperties(updateHabitInfo, habits);

        LocalDateTime localDateTime = LocalDateTime.parse(updateHabitInfo.getFinalDate() + " 00:00:00.000000",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        habits.setFinalDate(localDateTime);
        habitRepository.save(habits);
        return habits;
    }
}
