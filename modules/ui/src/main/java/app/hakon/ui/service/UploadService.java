package app.hakon.ui.service;

import app.hakon.ui.model.Tweet;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.URL;
import java.util.Collections;

@Service
public class UploadService {

    String BASE_URL = "http://localhost:9000/img";
    private RestTemplate restTemplate = new RestTemplate();



    public String uploadImg(MultipartFile img) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        File file = convert(img);
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        body.add("file", fileSystemResource);

        HttpEntity<Object> entity = new HttpEntity<>(body, headers);

        String url = restTemplate.postForObject(BASE_URL, entity, String.class);

        // Deleting tmp file
        file.delete();

        return url;
    }

    public void save(Tweet tweetToSave, MultipartFile imagefile){

        // Check if tweet contains image
        if(!imagefile.isEmpty()) {
            try{
                URL url = new URL(uploadImg(imagefile));

                if(url.equals(null)){
                    System.out.println("ERROR");
                } else {
                    tweetToSave.setImageUrl(url);
                }


            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }


    public File convert (MultipartFile img) {

        if(img.getOriginalFilename() != null){
            File converted = new File(img.getOriginalFilename());

            try{
                FileOutputStream fileOutputStream = new FileOutputStream(converted);
                fileOutputStream.write(img.getBytes());
                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return converted;
        }
        return null;
    }






}
