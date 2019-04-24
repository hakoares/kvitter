package app.hakon.follow.module;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class FollowerList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private long id;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="follows")
    private Set<User> follows;


    public boolean follow(long userid){

    }

}