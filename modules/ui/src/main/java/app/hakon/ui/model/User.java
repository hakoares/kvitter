package app.hakon.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private Roles roles;
    private URL profilePictureUrl;
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "friends")
    private List<User> friends;

    private String password;
    private String email;


    @OneToMany(mappedBy = "user")
    private List<UITweet> tweets;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePictureUrl=" + profilePictureUrl +
                ", description='" + description + '\'' +
                ", friends=" + friends +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
