package app.hakon.follow.service;

import app.hakon.follow.module.FollowUser;
import app.hakon.follow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public FollowUser save(FollowUser followUser){
        return userRepository.save(followUser);
    }

    public FollowUser findById(long id){
        return userRepository.findById(id);
    }
}
