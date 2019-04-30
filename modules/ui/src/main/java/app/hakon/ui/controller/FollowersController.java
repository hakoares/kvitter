package app.hakon.ui.controller;

import app.hakon.ui.model.FollowUser;
import app.hakon.ui.model.FollowerList;
import app.hakon.ui.model.Tweet;
import app.hakon.ui.model.User;
import app.hakon.ui.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequestMapping("/followers")
@Controller
public class FollowersController {

    @Autowired
    TweetService tweetService;
    @Autowired
    Authorize authorize;

    @Autowired
    UserServices userServices;

    @Autowired
    UploadService uploadService;

    @Autowired
    FollowService followService;

    @GetMapping("")
    public String getApp(Model model){
        authorize.isAuthorized(model);
        model.addAttribute("authorize", authorize);

        FollowerList fl = followService.getById(authorize.getUser().get().getId());
        List<Tweet> allTweets = new ArrayList<>();

        for(FollowUser user : fl.getFollows()) {
            for(Tweet t : tweetService.getTweetByUserId(user.getId())) {
                allTweets.add(t);
            }
        }

        for(Tweet t : tweetService.getTweetByUserId(authorize.getUser().get().getId())){
            allTweets.add(t);
        }

        // Sorting tweets in order by date
        Collections.sort(allTweets, new Comparator<Tweet>() {
            public int compare(Tweet o1, Tweet o2) {
                return o1.getCreatedAt().compareTo(o2.getCreatedAt());
            }
        });

        Collections.reverse(allTweets);

        model.addAttribute("us", userServices);
        model.addAttribute("tweet", new Tweet());
        model.addAttribute("tweets", allTweets);
        return "followers";
    }

    // Post tweet
    @PostMapping("/post")
    public String postTweet(@ModelAttribute("tweet") Tweet tweet, @RequestParam("img") MultipartFile imagefile, Model model)  {

        User user = authorize.getUser().get();
        Tweet tweetToSave = new Tweet(tweet.getMessage(), tweet.getImageUrl(), user.getId());

        uploadService.save(tweetToSave, imagefile);

        tweetService.save(tweetToSave);
        return "redirect:/followers";

    }

    @GetMapping("/retweet/{tweetId}")
    public String retweet(@PathVariable(name="tweetId") long tweetId){
        User user = authorize.getUser().get();

        Tweet retweet = tweetService.getTweetById(tweetId);
        Tweet tweetToSave = new Tweet();
        tweetToSave.setUserId(user.getId());
        tweetToSave.setRetweet(retweet);

        tweetService.save(tweetToSave);
    return "redirect:/followers";
    }

}
