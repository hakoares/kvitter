package app.hakon.ui.service;

import app.hakon.ui.model.Tweet;
import app.hakon.ui.model.UITweet;
import app.hakon.ui.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
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
            UITweet t1 = new UITweet(tweet.getMessage(), tweet.getCreatedAt(), tweet.getImageUrl(), userServices.findUserById(tweet.getId()));
            System.out.println(t1.getUser().toString());

            t1.setId(tweet.getId());
            uiTweets.add(t1);
        }

        return uiTweets;
    }

}
