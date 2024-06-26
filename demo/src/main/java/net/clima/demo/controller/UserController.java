package net.clima.demo.controller;

import lombok.AllArgsConstructor;
import net.clima.demo.model.dtos.CreateUserDTO;
import net.clima.demo.model.dtos.UserEditDTO;
import net.clima.demo.model.dtos.UserLoginDTO;
import net.clima.demo.model.entity.User;
import net.clima.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    private ResponseEntity<?> save(@RequestBody CreateUserDTO createUserDTO){
        System.out.println(createUserDTO);
        try{
            return new ResponseEntity<>(userService.save(createUserDTO), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findOne(@PathVariable Long id){
        try{
            return new ResponseEntity<>(userService.findOne(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PatchMapping
    private ResponseEntity<?> update(@RequestBody UserEditDTO user){
        try{
            return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/habit/{userId}")
    private ResponseEntity<?> getHabitsDone(Long userId){
        try{
            return new ResponseEntity<>(userService.getQuantityDone(userId), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO){
        try{
            return new ResponseEntity<>(userService.login(userLoginDTO), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/teste/{email}")
    private ResponseEntity<?> login(@PathVariable String email){
        try{
            return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
