package app.hakon.ui.service;

import app.hakon.ui.model.Tweet;
import app.hakon.ui.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public List<Tweet> tweetsBySearchTerm(String searchTerm){
        return Arrays.stream(restTemplate.getForObject(BASE_URL+"/search/"+searchTerm, Tweet[].class)).collect(Collectors.toList());
    }

    public void search(String keyword, Model model){
        Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
        Matcher mat = MY_PATTERN.matcher(keyword);
        List<String> strs = new ArrayList<String>();
        while (mat.find()) {
            strs.add(mat.group(1));
        }
        if(strs.size() == 1) {
            String tag = strs.get(0);
            model.addAttribute("tweets", tweetsBySearchTerm(tag));
        } else if(strs.size() > 1) {
            List<Tweet> searchRes = new ArrayList<>();
            for(String a : strs) {
                for(Tweet t : tweetsBySearchTerm(a)) {
                    if(!searchRes.contains(t)){
                        searchRes.add(t);
                    }
                }
            }
            model.addAttribute("tweets", searchRes);
        } else {
            List<Tweet> searchRes = new ArrayList<>();

            searchRes = tweetsBySearchTerm(keyword);
            if(searchRes.isEmpty()){
                model.addAttribute("tweets", null);
            } else {
                model.addAttribute("tweets", searchRes);

            }



        }
    }





}
