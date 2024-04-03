package net.clima.demo.controller;

import lombok.AllArgsConstructor;
import net.clima.demo.model.dtos.CreateUserDTO;
import net.clima.demo.model.dtos.HabitCreateDTO;
import net.clima.demo.model.dtos.UpdateHabitKind;
import net.clima.demo.service.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/habit")
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    private ResponseEntity<?> save(@RequestBody HabitCreateDTO habitCreateDTO){
        System.out.println(habitCreateDTO);
        try{
            return new ResponseEntity<>(habitService.save(habitCreateDTO), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/user/{userId}")
    private ResponseEntity<?> getAllByUser(@PathVariable Long userId){
        try{
            return new ResponseEntity<>(habitService.findByUser(userId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{habitId}")
    private ResponseEntity<?> deleteHabit(@PathVariable Long habitId){
        try{
            habitService.deleteHabit(habitId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping
    private ResponseEntity<?> changeKind(@RequestBody UpdateHabitKind updateHabitKind){
        try{
            habitService.changeKind(updateHabitKind);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
