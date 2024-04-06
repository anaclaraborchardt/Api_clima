package net.clima.demo.controller;

import lombok.AllArgsConstructor;
import net.clima.demo.model.dtos.HabitCreateDTO;
import net.clima.demo.model.dtos.UpdateHabit;
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

    @PostMapping
    private ResponseEntity<?> save(@RequestBody DailyGoal dailyGoal){
        System.out.println(dailyGoal);
        try{
            return new ResponseEntity<>(dailyGoalService.save(dailyGoal), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping("/current-status")
    private ResponseEntity<?> updateQuantity(@RequestBody UpdateHabit updateHabit){
        try{
            return new ResponseEntity<>(dailyGoalService.update(updateHabit), HttpStatus.OK);
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
}
