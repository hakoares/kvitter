package app.hakon.ui.model;

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



    public boolean follow(FollowUser user){
        follows.add(user);
        return true;
    }

    public boolean unFollow(FollowUser user) {
        follows.remove(user);
        return true;
    }

    public boolean followCheck(User user) {

        if(follows.contains(new FollowUser(user.getId()))){
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "FollowerList{" +
                "listid=" + listid +
                '}';
    }
}