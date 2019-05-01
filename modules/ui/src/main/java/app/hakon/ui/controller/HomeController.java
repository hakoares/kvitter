package app.hakon.ui.controller;


import app.hakon.ui.model.Roles;
import app.hakon.ui.model.Tweet;
import app.hakon.ui.model.User;
import app.hakon.ui.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    Authorize authorize;

    @Autowired
    LoginServices loginServices;

    @Autowired
    UserServices userServices;

    @Autowired
    TweetService tweetService;

    @Autowired
    FollowService followService;


    @GetMapping("/")
    public String getHome(Model model) {

        if(authorize.getIsAuthorized()) {
            return "redirect:/app";
        } else {
            authorize.isAuthorized(model);
            model.addAttribute("authorize", authorize);

            model.addAttribute("us", userServices);
            model.addAttribute("tweets", tweetService.getAll());
            return "index";
        }
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        return "signin";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/processRegistration")
    public String register(@ModelAttribute("user") User user, @RequestParam(name = "passConfirm") String passConfirm) {
        if (userServices.validation(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword())) {

            System.out.println(user.getUsername());

            //Check if user exists
            if (userServices.userExists(user.getEmail())) {
                return "redirect:/signup?error=exists";
            } else {

                // Checks that the passwords matches.
                if (!userServices.passwordMatch(user.getPassword(), passConfirm)) {
                    return "redirect:/signup?error=mismatch";
                } else {
                    String Password = loginServices.encodePassword(user.getPassword());
                    user.setPassword(Password);
                    user.setRoles(Roles.USER);

                    // Creates a followerlist in Follow API
                    followService.createList(user.getId());
                    userServices.save(user);

                    return "redirect:/";
                }
            }
        }
        return "redirect:/signup?error=nodata";
    }
}

