package app.hakon.upload.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

//Dir specified in application.properties
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {


    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
