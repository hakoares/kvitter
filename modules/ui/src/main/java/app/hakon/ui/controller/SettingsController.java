package app.hakon.ui.controller;


import app.hakon.ui.model.Tweet;
import app.hakon.ui.model.User;
import app.hakon.ui.service.Authorize;
import app.hakon.ui.service.LoginServices;
import app.hakon.ui.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/settings")
@Controller
public class SettingsController {

    @Autowired
    Authorize authorize;

    @Autowired
    UserServices userServices;

    @Autowired
    LoginServices loginServices;

    // TODO: 2019-04-25
    @GetMapping("")
    public String ViewSettings(Model model){
        authorize.isAuthorized(model);


        model.addAttribute("otheruser", authorize.getUser().get());
        model.addAttribute("authorize", authorize);
        model.addAttribute("us", userServices);
        return "settings";
    }

    @PostMapping("/changepassword")
    public String changePassword(@RequestParam(name="oldPassword") String oldPassword, @RequestParam(name="newPassword") String newPassword, @RequestParam(name="newPasswordConfirm") String newPasswordConfirm, HttpServletRequest request){

        if(loginServices.match(oldPassword, authorize.getUser().get().getPassword())){

            if(userServices.passwordMatch(newPassword, newPasswordConfirm)){
                User user = authorize.getUser().get();
                user.setPassword(loginServices.encodePassword(newPassword));
                userServices.save(user);

                return "redirect:"+request.getHeader("Referer")+"?saved";
            } else {
                return "redirect:"+request.getHeader("Referer")+"?error";
            }
        } else {
            return "redirect:"+request.getHeader("Referer")+"?error";
        }
    }

    @PostMapping("/saveprofile")
    public String saveProfile(@RequestParam(name="firstname") String firstname, @RequestParam(name="lastname") String lastname, @RequestParam(name="description") String description, HttpServletRequest request){


        User user = authorize.getUser().get();
        user.setDescription(description);
        user.setFirstName(firstname);
        user.setLastName(lastname);

        userServices.save(user);

        return "redirect:"+request.getHeader("Referer");
    }

    @GetMapping("/delete")
    public String deleteAccount(HttpServletRequest request){
        if(userServices.delete(authorize.getUser().get())) {
            authorize.setIsAuthorized(false);
            return ("redirect:/");
        }

        return "redirect:"+request.getHeader("Referer");
    }


}
