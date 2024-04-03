package net.clima.demo.service;

import lombok.AllArgsConstructor;
import net.clima.demo.model.dtos.UserEditDTO;
import net.clima.demo.model.entity.DailyGoal;
import net.clima.demo.model.entity.Habits;
import net.clima.demo.model.entity.User;
import net.clima.demo.model.dtos.CreateUserDTO;
import net.clima.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(CreateUserDTO createUserDTO){
        User user = new User();
        BeanUtils.copyProperties(createUserDTO, user);
        return userRepository.save(user);
    }

    public User findOne(Long id){
        return userRepository.findById(id).get();
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
}
