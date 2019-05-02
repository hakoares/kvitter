package app.hakon.tweets.controller;

import app.hakon.tweets.model.Tweet;
import app.hakon.tweets.repository.TweetRepository;
import app.hakon.tweets.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    TweetService tweetService;

    @GetMapping("/tweets")
    public List<Tweet> getAllTweets(){
        List<Tweet> tweets = tweetService.findAllOrdered();
        return tweets;
    }

    @GetMapping("/tweets/{id}")
    public Tweet getTweet(@PathVariable String id){
        long tweetId = Long.parseLong(id);
        return tweetService.getTweetById(tweetId);
    }

    @GetMapping("/tweets/search/{keyword}")
    public List<Tweet> search(@PathVariable String keyword){

        return tweetService.search(keyword);
    }

    @GetMapping("/tweets/user/{id}")
    public List<Tweet> getTweetByUser(@PathVariable String id){
        long userId = Long.parseLong(id);
        return tweetService.getAllByUserId(userId);
    }



    @DeleteMapping("/tweets/{id}")
    public void deleteTweet(@PathVariable String id){
        long tweetId = Long.parseLong(id);
        tweetService.deleteById(tweetId);
    }

    @PostMapping("/tweets")
    public void createTweet(@RequestBody Tweet tweet){
         tweetService.save(tweet);
    }

    @PutMapping("/tweets/{id}")
    public void updateTweet(@PathVariable String id, @RequestBody Tweet tweet){
        long tweetId = Long.parseLong(id);
        tweet.setId(tweetId);
        tweetService.save(tweet);
    }


}
