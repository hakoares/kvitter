package app.hakon.ui.controller;

import app.hakon.ui.model.Tweet;
import app.hakon.ui.service.Authorize;
import app.hakon.ui.service.TweetService;
import app.hakon.ui.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/search")
@Controller
public class SearchController {

    @Autowired
    TweetService tweetService;

    @Autowired
    Authorize authorize;

    @Autowired
    UserServices userServices;

    @GetMapping({"/",""})
    public String search(@RequestParam("keyword") String keyword, Model model){
        authorize.isAuthorized(model);

        if(keyword.equals("#") || keyword.isEmpty()){
            return "redirect:/error";
        } else {
        tweetService.search(keyword, model);
        }


        model.addAttribute("keyword", keyword);
        model.addAttribute("authorize", authorize);
        model.addAttribute("us", userServices);
        model.addAttribute("tweet", new Tweet());

        return "app";

    }
}
