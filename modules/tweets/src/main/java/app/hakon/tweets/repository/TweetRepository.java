package app.hakon.tweets.repository;

import app.hakon.tweets.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    public List<Tweet> findAllByOrderByCreatedAtDesc();

    public List<Tweet> getAllByUserIdOrderByCreatedAtDesc(long userId);

    public void deleteById(long id);

    public Tweet getTweetById(long id);

    public List<Tweet> findAllByMessageContainsOrderByCreatedAtDesc(String keyword);

}
