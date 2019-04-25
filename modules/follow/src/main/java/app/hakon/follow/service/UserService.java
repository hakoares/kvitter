package app.hakon.follow.service;

import app.hakon.follow.module.User;
import app.hakon.follow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User findById(long id){
        return userRepository.findById(id);
    }
}
