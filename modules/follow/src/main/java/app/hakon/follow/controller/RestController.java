package app.hakon.follow.controller;

import app.hakon.follow.module.Follow;
import app.hakon.follow.module.FollowerList;
import app.hakon.follow.service.FollowService;
import app.hakon.follow.service.FollowerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    FollowerListService followerListService;

    @GetMapping("/followers/{id}")
    public Set<FollowerList> follow (@PathVariable String id){
        long userid = Long.parseLong(id);


        return followerListService.findAllById(userid);
    }

    @GetMapping("/followers/{you}/{follow}")
    public boolean addFollower(@PathVariable("you") String you, @PathVariable("follow") String follow) {
        long youid = Long.parseLong(you);
        long followid = Long.parseLong(follow);



        return true;
    }
}
