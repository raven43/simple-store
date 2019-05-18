package com.raven43.simplestore.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileService {

    @Value("${simplestore.upload.path}")
    private String PATH;
    @Value("${simplestore.upload.maxNameLength}")
    private int MAX_LENGTH;

    public String upload(@NotNull MultipartFile file) throws IOException {
        File dir = new File(PATH);
        if (!dir.exists()) dir.mkdir();

        StringBuilder resultFileName = new StringBuilder();
        resultFileName
                .append(UUID.randomUUID().toString())
                .append(".")
                .append(file.getOriginalFilename());

        String result = (resultFileName.length() > MAX_LENGTH)
                ? resultFileName.substring(resultFileName.length() - MAX_LENGTH, resultFileName.length() - 1)
                : resultFileName.toString();

        file.transferTo(new File(dir.getAbsolutePath() + "/" + result));
        return "/file/" + result;
    }
}
