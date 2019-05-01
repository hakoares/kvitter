package app.hakon.ui.controller;

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

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    Authorize authorize;

    @Autowired
    UserServices userServices;

    @Autowired
    LoginServices loginServices;

    @GetMapping({"/",""})
    public String home(Model model) {
        authorize.isAuthorized(model);


        model.addAttribute("allusers", userServices.getAllUser());
        model.addAttribute("authorize", authorize);
        return "admin";
    }

    @GetMapping("/edit/{userid}")
    public String editUser(Model model, @PathVariable String userid) {

        User user = userServices.findUserById(Long.parseLong(userid)).get();

        authorize.isAuthorized(model);


        model.addAttribute("usertoedit", user);
        model.addAttribute("authorize", authorize);
        model.addAttribute("us", userServices);


        return "adminsettings";
    }

    @PostMapping("/changepassword")
    public String changePassword(@RequestParam(name="id") String userid, @RequestParam(name="newPassword") String newPassword, @RequestParam(name="newPasswordConfirm") String newPasswordConfirm, HttpServletRequest request){
        long id = Long.parseLong(userid);

        User user = userServices.findUserById(id).get();

         if(userServices.passwordMatch(newPassword, newPasswordConfirm)){
                user.setPassword(loginServices.encodePassword(newPassword));
                userServices.save(user);

            }

        return "redirect:"+request.getHeader("Referer")+"?saved";

    }

    @GetMapping("/delete/{userid}")
    public String deleteAccount(@PathVariable String userid){
        long id = Long.parseLong(userid);

        userServices.delete(userServices.findUserById(id).get());

        return "redirect:/admin";
    }


}
