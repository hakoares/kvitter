package app.hakon.ui.service;

import app.hakon.ui.model.Tweet;
import app.hakon.ui.model.UITweet;
import app.hakon.ui.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService {
    @Autowired
    UserServices userServices;


    String BASE_URL = "http://localhost:8090/tweets";
    private RestTemplate restTemplate = new RestTemplate();

    public List<UITweet> getAll() {
        List<Tweet> tweets =  Arrays.stream(restTemplate.getForObject(BASE_URL, Tweet[].class)).collect(Collectors.toList());
        List<UITweet> uiTweets = new ArrayList<>();

        for (Tweet tweet : tweets){
            if(userServices.findUserById(tweet.getUser()).isPresent()){
                User user = userServices.findUserById(tweet.getUser()).get();
                UITweet t1 = new UITweet(tweet.getMessage(), tweet.getCreatedAt(), tweet.getImageUrl(), user);
                t1.setId(tweet.getId());
                uiTweets.add(t1);
            }
        }

        Collections.reverse(uiTweets);
        return uiTweets;
    }

    public Tweet save(UITweet tweet){
        Tweet tweetToSave = new Tweet(tweet.getMessage(),tweet.getCreatedAt(),tweet.getImageUrl(), tweet.getUser().getId());
        return restTemplate.postForObject(BASE_URL, tweetToSave, Tweet.class);
    }


}
