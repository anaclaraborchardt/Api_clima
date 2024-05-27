package net.clima.demo.controller;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.clima.demo.model.entity.Habits;
import net.clima.demo.repository.HabitRepository;
import net.clima.demo.service.HabitService;
import net.clima.demo.service.UserService;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Data
@Configuration
public class DatabaseConfig {

    private final UserService userService;
    private final HabitRepository habitRepository;
    @PostConstruct
    public void init(){
//        Habits habit = new Habits();
//        try {
//            habit.setName("Beber água");
//            habit.setUser(userService.findOne(1L));
//            habit.setColor("Azul");
//
//            habitRepository.save(habit);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }
}
