package app.hakon.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class UITweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id")
    private long id;
    private String message;
    private Date createdAt;
    private URL imageUrl;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    public UITweet(String message, URL imageUrl, User user) {
        this.message = message;
        this.imageUrl = imageUrl;
        this.user = user;
        ZonedDateTime zdt = ZonedDateTime.now();
        this.createdAt = java.util.Date.from(zdt.toInstant());
    }

    public UITweet(String message, Date createdAt, URL imageUrl, User user) {
        this.message = message;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public UITweet(String message, Date createdAt, URL imageUrl) {
        this.message = message;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
    }

    public String getUsername(){
        return user.getUsername();
    }

    public String tweetDate(){
        return new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(createdAt);
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
