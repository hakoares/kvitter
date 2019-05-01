package app.hakon.ui.controller;

import app.hakon.ui.model.Tweet;
import app.hakon.ui.service.Authorize;
import app.hakon.ui.service.TweetService;
import app.hakon.ui.service.UploadService;
import app.hakon.ui.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppErrorController implements ErrorController {

    @Autowired
    TweetService tweetService;
    @Autowired
    Authorize authorize;

    @Autowired
    UserServices userServices;

    @Autowired
    UploadService uploadService;

    @RequestMapping("/error")
    public String handleError(Model model) {
        authorize.isAuthorized(model);
        model.addAttribute("authorize", authorize);


        model.addAttribute("us", userServices);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
