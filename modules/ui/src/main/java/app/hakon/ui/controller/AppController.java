package app.hakon.ui.controller;

import app.hakon.ui.model.Tweet;
import app.hakon.ui.model.User;
import app.hakon.ui.service.Authorize;
import app.hakon.ui.service.TweetService;
import app.hakon.ui.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    // Post tweet
    @PostMapping("/post")
    public String postTweet( @ModelAttribute("tweet") Tweet tweet, Model model) {

        User user = authorize.getUser().get();
        Tweet tweetToSave = new Tweet(tweet.getMessage(), tweet.getImageUrl(), user.getId());
        tweetService.save(tweetToSave);
        return "redirect:/app";

    }

    @GetMapping("/retweet/{tweetId}")
    public String retweet(@PathVariable(name="tweetId") long tweetId){
        User user = authorize.getUser().get();

        Tweet retweet = tweetService.getTweetById(tweetId);
        Tweet tweetToSave = new Tweet();
        tweetToSave.setUserId(user.getId());
        tweetToSave.setRetweet(retweet);

        tweetService.save(tweetToSave);
    return "redirect:/app";
    }

}
