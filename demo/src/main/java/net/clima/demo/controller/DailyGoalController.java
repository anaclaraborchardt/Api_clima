package net.clima.demo.controller;

import lombok.AllArgsConstructor;
import net.clima.demo.model.dtos.UpdateDailyGoal;
import net.clima.demo.model.dtos.UpdateQuantity;
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

    @GetMapping("/daily/{dailyId}")
    private ResponseEntity<?> findOne(@PathVariable Long dailyId){
        try{
            return new ResponseEntity<>(dailyGoalService.findOne(dailyId), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping("/quantity")
    private ResponseEntity<?> updateQuantity(@RequestBody UpdateQuantity updateQuantity){
        try{
            dailyGoalService.updateQuantity(updateQuantity);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{day}/{month}/habit/{habit}")
    private ResponseEntity<?> findByDate(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Long habit){
        try{
            return new ResponseEntity<>(dailyGoalService.findByDayAndHabit(day, month, habit), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
