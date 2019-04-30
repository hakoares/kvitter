package app.hakon.follow.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="follow_users")
public class FollowUser {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "follows")
    List<FollowerList> followerList;



}
