package app.hakon.ui.controller;


import app.hakon.ui.model.Tweet;
import app.hakon.ui.service.Authorize;
import app.hakon.ui.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/settings")
@Controller
public class SettingsController {

    @Autowired
    Authorize authorize;

    @Autowired
    UserServices userServices;

    // TODO: 2019-04-25
    @GetMapping("")
    public String ViewSettings(Model model){
        authorize.isAuthorized(model);


        model.addAttribute("otheruser", authorize.getUser().get());
        model.addAttribute("authorize", authorize);
        model.addAttribute("us", userServices);
        return "settings";
    }


}
