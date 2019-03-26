package app.hakon.tweets.service;

import app.hakon.tweets.model.Tweet;
import app.hakon.tweets.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    @Autowired
    TweetRepository tweetRepository;

    public List<Tweet> findAll(){
        return tweetRepository.findAll();
    }

    public List<Tweet> getAllByUser(long userID){
        return tweetRepository.getAllByUser(userID);
    }

    public void save(Tweet tweet){
        tweetRepository.save(tweet);
    }

    public void delete(Tweet tweet){
        tweetRepository.delete(tweet);
    }

}
