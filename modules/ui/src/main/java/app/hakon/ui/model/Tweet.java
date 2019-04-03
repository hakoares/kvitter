package app.hakon.ui.model;

import app.hakon.ui.service.UserServices;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
public class Tweet {

    private long id;
    private String message;
    private Date createdAt;
    private URL imageUrl;

    private long userId;


    public Tweet(String message, URL imageUrl, long userId) {
        this.message = message;
        this.imageUrl = imageUrl;
        this.userId = userId;
        ZonedDateTime zdt = ZonedDateTime.now();
        this.createdAt = Date.from(zdt.toInstant());
    }

    public Tweet(String message, Date createdAt, URL imageUrl, long userId) {
        this.message = message;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }

    public Tweet(String message, long userId) {
        this.message = message;
        this.userId = userId;
        ZonedDateTime zdt = ZonedDateTime.now();
        this.createdAt = Date.from(zdt.toInstant());
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
       return String.valueOf(userId);
    }

    public String tweetDate() {
        return new SimpleDateFormat("HH.mm MMM. dd.").format(createdAt);
    }

    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(createdAt);
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


