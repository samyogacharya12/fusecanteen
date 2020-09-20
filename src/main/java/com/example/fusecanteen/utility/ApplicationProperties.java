package com.example.fusecanteen.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    public String uploadDir = "/tmp/";
    public String getUploadDir() {
        return uploadDir;
    }
}
