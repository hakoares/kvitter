package app.hakon.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;
import java.time.Instant;
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    public Tweet(String message, URL imageUrl, User user) {
        this.message = message;
        this.imageUrl = imageUrl;
        this.user = user;
        ZonedDateTime zdt = ZonedDateTime.now();
        this.createdAt = java.util.Date.from(zdt.toInstant());
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
