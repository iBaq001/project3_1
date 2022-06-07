package com.amr.project.util;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class ImagesToByteArrayConverter {
private final String path = "classpath:static/images/";

    public byte[] getBiteArrayImage(String name) throws IOException {
        File fileImage = ResourceUtils.getFile(path + name);
        byte[] arrayImage = Files.readAllBytes(fileImage.toPath());
        return arrayImage;
    }
}
