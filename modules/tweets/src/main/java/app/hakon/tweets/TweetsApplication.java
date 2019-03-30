package app.hakon.tweets;

import app.hakon.tweets.model.Tweet;
import app.hakon.tweets.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URL;

@SpringBootApplication
public class TweetsApplication implements CommandLineRunner {

    @Autowired
    TweetService tweetService;

    public static void main(String[] args) {
        SpringApplication.run(TweetsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Tweet t1 = new Tweet("Min første twittermelding", new URL("http://nrk.no"), 1);

        tweetService.save(t1);


    }
}
