package app.hakon.ui.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.net.URL;

import java.io.*;

@Service
public class UploadService {

    String BASE_URL = "http://localhost:9000/img";
    private RestTemplate restTemplate = new RestTemplate();

    public void save(MultipartFile img){

        System.out.println("\nimg.save:");

        try {
            System.out.println("try");

            restTemplate.postForObject(BASE_URL, img, MultipartFile.class);


        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }



}
