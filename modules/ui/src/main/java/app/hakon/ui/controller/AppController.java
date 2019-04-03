package app.hakon.ui.controller;

import app.hakon.ui.model.Tweet;
import app.hakon.ui.model.User;
import app.hakon.ui.service.Authorize;
import app.hakon.ui.service.TweetService;
import app.hakon.ui.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/app")
@Controller
public class AppController {

    @Autowired
    TweetService tweetService;
    @Autowired
    Authorize authorize;

    @Autowired
    UserServices userServices;

    @GetMapping("")
    public String getApp(Model model){
        authorize.isAuthorized(model);
        model.addAttribute("authorize", authorize);


        List<Tweet> allTweets = tweetService.getAll();

        model.addAttribute("tweet", new Tweet());
        model.addAttribute("tweets", allTweets);
        return "app";
    }

    @PostMapping("/post")
    public String postTweet(@ModelAttribute("tweet") Tweet tweet) {
        User user = authorize.getUser().get();
        Tweet tweetToPost = new Tweet(tweet.getMessage(), tweet.getImageUrl(), user.getId());
        tweetService.save(tweetToPost);
        System.out.println(tweet.getUsername());

        return "redirect:/app";
    }

}
