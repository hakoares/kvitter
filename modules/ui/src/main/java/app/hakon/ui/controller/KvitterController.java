package app.hakon.ui.controller;

import app.hakon.ui.model.UITweet;
import app.hakon.ui.model.User;
import app.hakon.ui.service.Authorize;
import app.hakon.ui.service.TweetService;
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
public class KvitterController {

    @Autowired
    TweetService tweetService;
    @Autowired
    Authorize authorize;

    @GetMapping("")
    public String getApp(Model model){
        authorize.isAuthorized(model);
        model.addAttribute("authorize", authorize);

        List<UITweet> allTweets = tweetService.getAll();

        model.addAttribute("tweet", new UITweet());
        model.addAttribute("tweets", allTweets);
        return "app";
    }

    @PostMapping("/post")
    public String postTweet(@ModelAttribute("tweet") UITweet tweet) {
        User user = authorize.getUser().get();
        UITweet tweetToPost = new UITweet(tweet.getMessage(), tweet.getImageUrl(), user);
        tweetService.save(tweetToPost);
        return "redirect:/app";
    }
}
