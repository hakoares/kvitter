package app.hakon.ui.service;

import app.hakon.ui.model.FollowerList;
import app.hakon.ui.model.Tweet;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowService {

    String BASE_URL = "http://localhost:9090/";
    private RestTemplate restTemplate = new RestTemplate();

    public FollowerList getById(long id) {
        return restTemplate.getForObject(BASE_URL+"followers/"+id, FollowerList.class);

    }

    public String createList(long id){
        return restTemplate.getForObject(BASE_URL + "addlist/"+id, String.class);
    }
}
