package app.hakon.ui.service;

import app.hakon.ui.model.Tweet;
import app.hakon.ui.model.User;
import app.hakon.ui.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginServices loginServices;


    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public Optional<User> findUserById(long id){
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
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

    public List<User> getAllUser() {
        return userRepository.findAll();
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

    // Used to display username in feed
    public String getUsername(long userid){
        return findUserById(userid).get().getUsername();
    }



}
