package app.hakon.ui.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UploadService {

    String BASE_URL = "http://localhost:9000/img";
    private RestTemplate restTemplate = new RestTemplate();

    

}
