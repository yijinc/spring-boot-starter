package com.fish.myspringboot.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RequestMapping("/file")
@RestController
public class FileController {

    @RequestMapping(value="/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File("/Users/y/Downloads" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream out = new FileOutputStream(convertFile);
        out.write(file.getBytes());
        out.close();
        return "File is upload successfully";
    }

    @RequestMapping(value = "/download/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Object> downloadFile(@PathVariable("fileName") String fileName) throws IOException {
        String filename = "/Users/y/Downloads/" + fileName;
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
                MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }
}