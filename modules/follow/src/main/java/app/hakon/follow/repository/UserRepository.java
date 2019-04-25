package app.hakon.follow.repository;

import app.hakon.follow.module.FollowUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<FollowUser, Long> {

    public FollowUser save(FollowUser followUser);

    public FollowUser findById(long id);

}
