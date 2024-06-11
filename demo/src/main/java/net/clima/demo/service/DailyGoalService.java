package net.clima.demo.service;

import lombok.AllArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;
import net.clima.demo.model.dtos.HabitCreateDTO;
import net.clima.demo.model.dtos.UpdateBoolean;
import net.clima.demo.model.dtos.UpdateDailyGoal;
import net.clima.demo.model.dtos.UpdateQuantity;
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
import java.util.Objects;

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

            if (kind == GoalKind.quantidade) {
                Integer goal = Integer.valueOf(habitCreateDTO.getGoal());
                Quantity quantity = quantityRepository.save(new Quantity(goal));
                goalForDay.setQuantity(quantity);
            } else if (kind == GoalKind.booleano) {
                boolean goal = Boolean.parseBoolean(habitCreateDTO.getGoal());
                BooleanType bool = booleanTypeRepository.save(new BooleanType(goal));
                goalForDay.setBooleanS(bool);
            } else if (kind == GoalKind.tempo) {
                goalForDay.setTime(dailyGoal.getTime());
            }
            dailyGoalRepository.save(goalForDay);
        }
    }

    public DailyGoal findById(Long id) {
        return dailyGoalRepository.findById(id).get();
    }

    public void update(UpdateDailyGoal updateHabit) {
        System.out.println(updateHabit.getHabitId());
        for (DailyGoal daily : dailyGoalRepository.findAll()) {
            if (Objects.equals(daily.getHabit().getId(), updateHabit.getHabitId())) {
                if (daily.getQuantity() != null) {
                    daily.getQuantity().setGoal(Integer.valueOf(updateHabit.getNewGoal()));
                    quantityRepository.save(daily.getQuantity());
                } else if (daily.getBooleanS() != null) {
                    daily.getBooleanS().setGoal(Boolean.parseBoolean(updateHabit.getNewGoal()));
                    booleanTypeRepository.save(daily.getBooleanS());
                }
            }
        }
    }

    public void setAsDone(DailyGoal dailyGoal) {
        if (dailyGoal.getQuantity() != null) {
            if (dailyGoal.getQuantity().getCurrentStatus().equals(dailyGoal.getQuantity().getGoal())) {
                dailyGoal.setDone(true);
                dailyGoalRepository.save(dailyGoal);
            }
        }
    }

    public List<Habits> getHabitsDoneInADay(Long userId) {
        List<Habits> habits = habitRepository.findAllByUser_Id(userId);
        List<Habits> completedHabits = new ArrayList<>();

        for (Habits habit1 : habits) {
            for (DailyGoal dailyGoal : habit1.getDailyGoalList()) {
                if (dailyGoal.getDay().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
                    completedHabits.add(habit1);
                }
            }
        }
        return completedHabits;
    }

    public List<DailyGoal> getAll(Long id) {
        System.out.println("dg" + id);
        return dailyGoalRepository.findDailyGoalByHabit_Id(id);
    }

    public DailyGoal findOne(Long dailyId) {
        return dailyGoalRepository.findById(dailyId).get();
    }

    public void updateQuantity(UpdateQuantity updateQuantity) {
        DailyGoal dailyGoal = findOne(updateQuantity.getId());
        dailyGoal.getQuantity().setCurrentStatus(updateQuantity.getNewQuantity());
        quantityRepository.save(dailyGoal.getQuantity());
        setAsDone(dailyGoal);
    }

    public void updateBoolean(UpdateBoolean updateBoolean) {
        DailyGoal dailyGoal = findOne(updateBoolean.getId());
        dailyGoal.getBooleanS().setCurrentStatus(updateBoolean.getNewBool());
        booleanTypeRepository.save(dailyGoal.getBooleanS());
        setAsDone(dailyGoal);
    }

    public void setAsDone(Long dailyId){
        System.out.println(dailyId);
        DailyGoal daily = dailyGoalRepository.findById(dailyId).get();
        if(daily.getQuantity() != null){
            daily.getQuantity().setCurrentStatus(daily.getQuantity().getGoal());
            quantityRepository.save(daily.getQuantity());
        } else {
            daily.getBooleanS().setCurrentStatus(daily.getBooleanS().isCurrentStatus());
        }
        daily.setDone(true);
        dailyGoalRepository.save(daily);
    }

    public DailyGoal findByDayAndHabit(Integer day, Integer month, Long id) {
        for (DailyGoal dailyGoal : dailyGoalRepository.findAll()) {
            if (dailyGoal.getDay().getDayOfMonth() == day) {
                if (dailyGoal.getDay().getMonthValue() == month) {
                    if (dailyGoal.getHabit().getId().equals(id)) {
                        return dailyGoal;
                    }
                }
            }
        }
        throw new RuntimeException("No dailyGoal for this habit in this day");
    }

    public List<DailyGoal> findAllByDay(Integer day, Integer month){
        List<DailyGoal> dailies = new ArrayList<>();
        for (DailyGoal dailyGoal : dailyGoalRepository.findAll()) {
            if (dailyGoal.getDay().getDayOfMonth() == day) {
                if (dailyGoal.getDay().getMonthValue() == month) {
                    dailies.add(dailyGoal);
                }
            }
        }
        return dailies;
    }

    public void delete(DailyGoal dailyGoal){
        dailyGoalRepository.delete(dailyGoal);
    }

}
