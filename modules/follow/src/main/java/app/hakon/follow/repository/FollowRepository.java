package app.hakon.follow.repository;

import app.hakon.follow.module.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    public Follow getFollowByUser(long userid);
}
