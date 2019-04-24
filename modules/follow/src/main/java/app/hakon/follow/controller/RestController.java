package app.hakon.follow.controller;

import app.hakon.follow.module.Follow;
import app.hakon.follow.service.FollowService;
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
    FollowService followService;

    @GetMapping("/followers/{id}")
    public Set<Long> follow (@PathVariable String id){
        long userid = Long.parseLong(id);

        return followService.getFollow(userid).getFollowers();
    }

    @GetMapping("/followers/{you}/{follow}")
    public boolean addFollower(@PathVariable("you") String you, @PathVariable("follow") String follow) {
        long youid = Long.parseLong(you);
        long followid = Long.parseLong(follow);

        System.out.println(youid + " - " + followid);

        Follow f0 = followService.getFollow(youid);

        f0.addFollowing(followService.getFollow(followid));

        followService.save(f0);

        return true;
    }
}
