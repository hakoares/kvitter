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
    private List<User> follows;



    public void setlistId(long id){
        this.listid = id;
    }



    public boolean follow(User user){
        follows.add(user);
        return true;
    }

    public boolean unFollow(User user) {
        follows.remove(user);
        return true;
    }

    @Override
    public String toString() {
        return "FollowerList{" +
                "listid=" + listid +
                '}';
    }
}