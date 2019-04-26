package app.hakon.ui.repository;

import app.hakon.ui.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(long id);

    public Boolean deleteById(long id);


}
