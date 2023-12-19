package com.romanyuta.todoproject.service;

import com.romanyuta.todoproject.model.Users;
import com.romanyuta.todoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<Users> getUsers(){
        return userRepository.findAll();
    }

    public Users getUserById(Long id){
        return userRepository.getById(id);
    }

    public void addNewUser(Users user){
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalStateException("user with id=" + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String name, String role) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id=" + userId + " does not exist"
                ));
        if(name != null && name.length()>0 && !Objects.equals(user.getName(),name)){
            user.setName(name);
        }
        if(role != null && role.length()>0 && !Objects.equals(user.getRole(),role)){
            user.setRole(role);
        }
    }
}
