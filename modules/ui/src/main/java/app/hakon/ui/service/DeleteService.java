package app.hakon.ui.service;

import app.hakon.ui.model.FollowUser;
import app.hakon.ui.model.FollowerList;
import app.hakon.ui.model.Tweet;
import app.hakon.ui.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteService {

    @Autowired
    UserServices userServices;

    @Autowired
    TweetService tweetService;

    @Autowired
    FollowService followService;


    // Method for deleting a user from the system completely
    public void delete(User user) {

        // Deleting tweets
        List<Tweet> tweetList = tweetService.getTweetByUserId(user.getId());
        for(Tweet t : tweetList) {
            tweetService.delete(t);
        }

        // Removing user from others followerlist
        List<User> allUsers = userServices.getAllUser();
        for(User u : allUsers) {
            FollowerList fl = followService.getById(u.getId());
            if(fl.followCheck(user)){
                followService.unFollow(u.getId(), user.getId());
            }
        }


        // Deleting following list
        followService.delete(user.getId());


        // Delete user
        userServices.delete(user);
    }

}
