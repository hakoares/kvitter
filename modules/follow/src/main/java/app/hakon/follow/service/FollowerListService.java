package app.hakon.follow.service;

import app.hakon.follow.module.FollowerList;
import app.hakon.follow.repository.FollowerListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FollowerListService {

    @Autowired
    FollowerListRepository followerListRepository;


    public FollowerList save(FollowerList followerList) {
        return followerListRepository.save(followerList);
    }

    public FollowerList findAllById(long id){
        return followerListRepository.findByListid(id);
    }

    public FollowerList findById(long id) {
        return followerListRepository.findByListid(id);
    }

}
