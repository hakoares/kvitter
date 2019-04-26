package app.hakon.ui.service;

import app.hakon.ui.model.Tweet;
import app.hakon.ui.model.User;
import app.hakon.ui.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginServices loginServices;

    @Autowired
    FollowService followService;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> findUserById(long id){
        return userRepository.findById(id);
    }

    public void save(User user) {

        // Creates a followerlist in Follow API
        followService.createList(user.getId());

        userRepository.save(user);
    }

    public Boolean delete(User user){
        return userRepository.deleteById(user.getId());

    }

    // Check if user already exists.
    public Boolean userExists(String username) {
        if (!userRepository.findUserByUsername(username).isPresent()) {
            return false;
        } else {
            return true;
        }
    }


    // Simple check that password matches.
    public Boolean passwordMatch(String pass1, String pass2) {
        if (!(pass1.equals(pass2))) {
            return false;
        }
        return true;

    }

    // Simple validation checking that all fields are filled in.
    public boolean validation(String fn, String ln, String em, String p) {
        if (fn == "") {
            return false;
        } else if (ln == "") {
            return false;
        } else if (em == "") {
            return false;
        } else if (p == "") {
            return false;
        }
        return true;
    }

    public String getUsername(long userid){
        return findUserById(userid).get().getUsername();
    }



}
