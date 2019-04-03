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

    @Column(name = "user_id")
    private long userId;


    public Tweet(String message, URL imageUrl, long userId) {
        this.message = message;
        this.imageUrl = imageUrl;
        this.userId = userId;
        ZonedDateTime zdt = ZonedDateTime.now();
        this.createdAt = Date.from(zdt.toInstant());
    }
    public Tweet(String message, long userId) {
        this.message = message;
        this.userId = userId;
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
                ", userId=" + userId +

                '}';
    }
}
