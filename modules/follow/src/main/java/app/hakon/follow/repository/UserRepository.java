package app.hakon.follow.repository;

import app.hakon.follow.module.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public User save(User user);

    public User findById(long id);

}
