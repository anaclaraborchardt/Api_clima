package net.clima.demo.controller;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.clima.demo.model.ENUM.GoalKind;
import net.clima.demo.model.ENUM.HabitCategory;
import net.clima.demo.model.dtos.CreateUserDTO;
import net.clima.demo.model.dtos.HabitCreateDTO;
import net.clima.demo.model.entity.Habits;
import net.clima.demo.model.entity.User;
import net.clima.demo.repository.HabitRepository;
import net.clima.demo.service.HabitService;
import net.clima.demo.service.UserService;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Data
@Configuration
public class DatabaseConfig {

    private final UserService userService;
    private final HabitService habitService;
    @PostConstruct
    public void init(){
        CreateUserDTO createUserDTO = new CreateUserDTO("ana", "123", "ana@gmail.com");
        User user = userService.save(createUserDTO);

        HabitCreateDTO habitCreateDTO1 = new HabitCreateDTO("Ler um livro", user, GoalKind.quantidade, HabitCategory.matutino,
                "2024-06-21","10", "páginas", "#ff51b9d6");
        HabitCreateDTO habitCreateDTO2 = new HabitCreateDTO("Beber água", user, GoalKind.quantidade, HabitCategory.diario,
                "2024-06-21","5", "copos", "#ff51b9d6");
        habitService.save(habitCreateDTO1);
        habitService.save(habitCreateDTO2);



    }
}
