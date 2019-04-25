package app.hakon.follow.controller;

import app.hakon.follow.module.FollowUser;
import app.hakon.follow.module.FollowerList;
import app.hakon.follow.service.FollowerListService;
import app.hakon.follow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    FollowerListService followerListService;

    @Autowired
    UserService userService;

    @GetMapping("/followers/{id}")
    public FollowerList follow (@PathVariable String id){
        long listid = Long.parseLong(id);


        return followerListService.findById(listid);
    }

    @GetMapping("/addlist/{id}")
    public String addListOnUser(@PathVariable String id) {
        long listid = Long.parseLong(id);

        FollowerList fl = new FollowerList();
        fl.setlistId(listid);

        followerListService.save(fl);

        return "Done";

    }

    @GetMapping("/follow/{a}/{b}")
    public String follow(@PathVariable("a") String a, @PathVariable("b") String b) {
        long listid = Long.parseLong(a);
        long otherid = Long.parseLong(b);

        FollowUser u1 = new FollowUser();
        u1.setId(otherid);
        FollowerList fl = followerListService.findById(listid);

        fl.follow(u1);

        followerListService.save(fl);
        return "FollowUser " + listid + " is now following user " + otherid;
    }

    @GetMapping("/unfollow/{a}/{b}")
    public String test(@PathVariable("a") String a, @PathVariable("b") String b) {
        long listid = Long.parseLong(a);
        long otherid = Long.parseLong(b);

        FollowerList fl = followerListService.findById(listid);
        FollowUser u1 = userService.findById(otherid);

        fl.unFollow(u1);

        followerListService.save(fl);
        return "FollowUser " + listid + " do not follow user " + otherid + " any more";
    }

    @GetMapping("/delete/{id}")
    public String deleteList(@PathVariable String id) {
        long listid = Long.parseLong(id);

        followerListService.delete(listid);

        return "List id: " + listid + " deleted.";
    }
}
