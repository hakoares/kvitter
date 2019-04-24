package app.hakon.follow.service;

import app.hakon.follow.module.Follow;
import app.hakon.follow.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    FollowRepository followRepository;

    public void save (Follow follow){
        followRepository.save(follow);

    }

    public Follow getFollow(long userId){
        return followRepository.getFollowByUser(userId);
    }
}
