package app.hakon.upload.controller;

import app.hakon.upload.model.ImgTypes;
import app.hakon.upload.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
public class FileController {

    @Autowired
    FileStorageService fileStorageService;


    @PostMapping("/img")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        // Check the filetype
        if(file.getContentType().contains(ImgTypes.gif) || file.getContentType().contains(ImgTypes.png) || file.getContentType().contains(ImgTypes.jpg)){
            String fileName = fileStorageService.storeFile(file);

            // Building URL
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/img/")
                    .path(fileName)
                    .toUriString();

            // Returning the URL used to return the image
            return fileDownloadUri;
        }

    return "Uploaded file is not a valid image. Only JPG, PNG and GIF files are allowed";

    }


    @GetMapping("/img/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {


        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Strange file type: " + e);
       }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        // attachment
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
