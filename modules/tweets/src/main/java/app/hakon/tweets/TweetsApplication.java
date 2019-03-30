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

        Tweet t1 = new Tweet("Tenk å lage sin egen twitter, nørd.", new URL("https://images.unsplash.com/photo-1535551951406-a19828b0a76b"), 1);

        tweetService.save(t1);


    }
}
