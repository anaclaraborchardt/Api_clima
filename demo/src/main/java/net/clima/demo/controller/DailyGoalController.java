package net.clima.demo.controller;

import lombok.AllArgsConstructor;
import net.clima.demo.model.dtos.UpdateDailyGoal;
import net.clima.demo.model.entity.DailyGoal;
import net.clima.demo.service.DailyGoalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/dailyGoal")
public class DailyGoalController {

    private final DailyGoalService dailyGoalService;

    @PatchMapping("/update-goal")
    private ResponseEntity<?> updateGoal(@RequestBody UpdateDailyGoal updateHabit){
        try{
            dailyGoalService.update(updateHabit);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/day/user/{userId}")
    private ResponseEntity<?> getHabitsDoneInADay(@PathVariable Long userId){
        try{
            return new ResponseEntity<>(dailyGoalService.getHabitsDoneInADay(userId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{habitId}")
    private ResponseEntity<?> findAll(@PathVariable Long habitId){
        try{
            return new ResponseEntity<>(dailyGoalService.getAll(habitId), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
