package org.example.web.controller;

import org.example.domain.ResponseResult;
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

    private static final String FILE_DEFAULT_DIRECTORY = "~/workspace/file-server";

    /**
     * 文件上传
     * 文件上传
     */
    @RequestMapping(value="/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseResult<?> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File(FILE_DEFAULT_DIRECTORY + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream out = new FileOutputStream(convertFile);
        out.write(file.getBytes());
        out.close();
        return ResponseResult.ok();
    }

    /**
     * 文件下载
     * 根据fileName 下载文件
     */
    @RequestMapping(value = "/download/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Object> downloadFile(@PathVariable("fileName") String fileName) throws IOException {
        String filename = FILE_DEFAULT_DIRECTORY + fileName;
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
                MediaType.parseMediaType("application/txt")).body(resource);
    }
}