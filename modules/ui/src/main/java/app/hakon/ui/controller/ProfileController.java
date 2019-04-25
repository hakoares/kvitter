package app.hakon.ui.controller;

import app.hakon.ui.model.Tweet;
import app.hakon.ui.service.Authorize;
import app.hakon.ui.service.FollowService;
import app.hakon.ui.service.TweetService;
import app.hakon.ui.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@RequestMapping("/profile")
@Controller
public class ProfileController {

    @Autowired
    TweetService tweetService;

    @Autowired
    Authorize authorize;

    @Autowired
    UserServices userServices;

    @Autowired
    FollowService followService;

    @GetMapping("/{id}")
    public String profile(Model model, @PathVariable String id){
        long userid = Long.parseLong(id);

        // User of the profile
        model.addAttribute("otheruser", userServices.findUserById(userid).get());


        authorize.isAuthorized(model);
        model.addAttribute("authorize", authorize);


        List<Tweet> allTweets = tweetService.getTweetByUserId(userid);
        Collections.reverse(allTweets);

        model.addAttribute("us", userServices);
        model.addAttribute("followlist", followService.getById(authorize.getUser().get().getId()));
        model.addAttribute("tweet", new Tweet());
        model.addAttribute("tweets", allTweets);

        return "profile";
    }
}
