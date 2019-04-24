package app.hakon.follow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/followers/{id}")
    public List<Long> getFollowers(@PathVariable String id){
        List<Long> list = new ArrayList<>();
        list.add((long) 3);
        return list;
    }
}
