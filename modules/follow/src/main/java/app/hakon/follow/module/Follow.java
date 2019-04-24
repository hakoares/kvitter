package app.hakon.follow.module;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Data
@Entity
@Table(name = "Followers")
public class Follow {

    @Id
    @Column(name = "user_id")
    private long user;

    @ManyToMany
    private Set<Follow> follows = new HashSet<>();

    public Follow() {
    }

    public void addFollowing(Follow follow){
        this.follows.add(follow);
    }

    public Set<Long> getFollowers(){
        Set<Long> followers = new HashSet<>();
        for(Follow follow : follows) {
            followers.add(follow.getUser());
        }
        return followers;
    }

    public Follow(long userid) {
        this.user = userid;
    }



    @Override
    public String toString() {
        return "Follow{" +
                "user=" + user +
                '}';
    }
}
