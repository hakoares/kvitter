package app.hakon.ui.service;

import app.hakon.ui.model.Tweet;
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

    public List<Tweet> getAll() {
        return Arrays.stream(restTemplate.getForObject(BASE_URL, Tweet[].class)).collect(Collectors.toList());

    }

    public Tweet save(Tweet tweet){
        return restTemplate.postForObject(BASE_URL, tweet, Tweet.class);
    }

    public Tweet getTweetById(long tweetId){
        return restTemplate.getForObject(BASE_URL+"/"+tweetId, Tweet.class);
    }

    public List<Tweet> getTweetByUserId(long userId){
        return Arrays.stream(restTemplate.getForObject(BASE_URL+"/user/"+userId, Tweet[].class)).collect(Collectors.toList());
    }

    public List<Tweet> search(String keyword){
        return Arrays.stream(restTemplate.getForObject(BASE_URL+"/search/"+keyword, Tweet[].class)).collect(Collectors.toList());
    }





}
