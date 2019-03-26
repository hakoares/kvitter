package app.hakon.tweets.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id")
    private long id;
    private String message;
    private Date createdAt;
    private URL imageUrl;

    private long user;


    public Tweet(String message, URL imageUrl, long user) {
        this.message = message;
        this.imageUrl = imageUrl;
        this.user = user;
        ZonedDateTime zdt = ZonedDateTime.now();
        this.createdAt = Date.from(zdt.toInstant());
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", imageUrl=" + imageUrl +
                '}';
    }
}
