package app.hakon.ui.service;

import app.hakon.ui.model.Roles;
import app.hakon.ui.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Data
@NoArgsConstructor
@Service
public class Authorize {

    @Autowired
    UserServices userServices;

    private Boolean isAuthorized = false;


    // DONE
    public boolean isAuthorized(Model model) {
        Optional<User> user = getUser();
        if (user.isPresent()) {
            isAuthorized = true;
            model.addAttribute("user", user.get());
            return true;
        } else {
            isAuthorized = false;
            return false;
        }
    }

    // DONE
    public Optional<User> getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userServices.findUserByEmail(auth.getName());
        return user;
    }

    // DONE     Method for checking if the User is authenticated
    public boolean isAuthorizedByRoles(Roles roles) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userServices.findUserByEmail(auth.getName());


        if (user.get().getRoles() == roles || user.get().getRoles() == Roles.ADMIN) {
            if (user.isPresent()) {
                isAuthorized = true;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
