package app.hakon.follow.module;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="follow_lists")
public class FollowerList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private long listid;



    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "follow_followinglist",
            joinColumns = { @JoinColumn(name = "listid") }, inverseJoinColumns = { @JoinColumn(name = "id") }
    )
    private List<FollowUser> follows;



    public void setlistId(long id){
        this.listid = id;
    }



    public boolean follow(FollowUser followUser){
        follows.add(followUser);
        return true;
    }

    public boolean unFollow(FollowUser followUser) {
        follows.remove(followUser);
        return true;
    }

    @Override
    public String toString() {
        return "FollowerList{" +
                "listid=" + listid +
                '}';
    }
}