package app.hakon.follow.repository;

import app.hakon.follow.module.FollowerList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FollowerListRepository extends JpaRepository<FollowerList, Long> {


    public FollowerList findByListid(long id);



}
