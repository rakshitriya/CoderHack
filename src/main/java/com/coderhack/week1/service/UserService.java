package com.coderhack.week1.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhack.week1.entity.Badges;
import com.coderhack.week1.entity.User;
import com.coderhack.week1.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
        user.setScore(0);
        user.getBadges().clear();
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, int newScore){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with "+userId+" was not found"));
        user.setScore(newScore);
        updateBadges(user);
        userRepository.save(user);
        return userRepository.save(user);
    }

    public List<User> getAllUsersSortedByScore(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(String userId){
        return userRepository.findById(userId);
    }

    public void deleteUserById(String userId){
        if(!userRepository.existsById(userId)){
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(userId);
    }
    private void updateBadges(User user){
        user.getBadges().clear();
        if(user.getScore()>=1 && user.getScore()<30){
            user.getBadges().add(Badges.CODE_NINJA);
        }
        if(user.getScore()>=30 && user.getScore()<60){
            user.getBadges().add(Badges.CODE_CHAMP);
        }
        if(user.getScore()>=60 && user.getScore()<100){
            user.getBadges().add(Badges.CODE_MASTER);
        }
    }
}
