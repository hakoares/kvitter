package app.hakon.ui.service;

import app.hakon.ui.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    }

}
