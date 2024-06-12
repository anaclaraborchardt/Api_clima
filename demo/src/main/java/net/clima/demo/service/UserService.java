package net.clima.demo.service;

import lombok.AllArgsConstructor;
import net.clima.demo.model.dtos.UserEditDTO;
import net.clima.demo.model.dtos.UserLoginDTO;
import net.clima.demo.model.entity.DailyGoal;
import net.clima.demo.model.entity.Habits;
import net.clima.demo.model.entity.User;
import net.clima.demo.model.dtos.CreateUserDTO;
import net.clima.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(CreateUserDTO createUserDTO){
        User user = new User();
        BeanUtils.copyProperties(createUserDTO, user);
        emailValidation(user);
        for(User userFor : findAll()){
            if(user.getEmail().equals(userFor.getEmail())){
                throw new RuntimeException("This email already exists");
            }
        }
        System.out.println("entrei");
        return userRepository.save(user);
    }

    public User findOne(Long id){
        return userRepository.findById(id).get();
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User update(UserEditDTO userEdit) {
        User user = findOne(userEdit.getId());
        if(userEdit.getName() == null){
          user.setName(user.getName());
        }else {
            user.setName(userEdit.getName());
        }
        if(userEdit.getEmail() == null){
            user.setEmail(user.getEmail());
        }else {
            user.setEmail(userEdit.getEmail());
        }
        if(userEdit.getPassword() == null){
            user.setPassword(user.getPassword());
        }else {
            user.setPassword(userEdit.getPassword());
        }
        emailValidation(user);
        return userRepository.save(user);
    }

    public Integer getQuantityDone(Long userId){
        int count = 0;
        User user = findOne(userId);
        for(Habits habit : user.getHabitsList()){
            for(DailyGoal daily: habit.getDailyGoalList()){
                if(daily.isDone()){
                    count = count + 1;
                }
            }
        }
    return count;
    }

    public User login(UserLoginDTO userLoginDTO){
        for(User user : findAll()){
            if(user.getEmail()!=null) {
                if (user.getEmail().equals(userLoginDTO.getEmail())) {
                    if (user.getPassword().equals(userLoginDTO.getPassword())) {
                        return user;
                    }
                }
            }
        }
        throw new RuntimeException("User not found");
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    private void emailValidation(User user){
        boolean validEmail = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
                .matcher(user.getEmail())
                .find();

        if (!validEmail) {
            throw new RuntimeException("The given email isn't correct");
        }
    }
}
