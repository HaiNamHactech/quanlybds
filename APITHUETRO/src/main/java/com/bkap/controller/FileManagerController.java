package com.bkap.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.bkap.utils.service.impl.FileStorageService;
import com.bkap.utils.FileResponse;
import com.bkap.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/api/ghost/filemanager")
@CrossOrigin(origins="*",allowedHeaders="*")
public class FileManagerController {

    @Autowired
    private FileStorageService fileService;

    @PostMapping(value = "/upload")
    public ResponseEntity<Response<FileResponse>> uploadFile(@RequestParam("file") MultipartFile file) {
        FileResponse data = saveFile(file);
        return new ResponseEntity<Response<FileResponse>>(
                new Response("upload file success",200,data,0), HttpStatus.OK
        );
//   or : return ResponseEntity.ok().body(new Response<FileResponse>("upload file success", 200, data));
    }

    @PostMapping(value = "/uploads")
    public ResponseEntity<Response<List<FileResponse>>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        List<FileResponse> data = Arrays.asList(files)
                .stream()
                .map(file -> saveFile(file))
                .collect(Collectors.toList());
        return new ResponseEntity<Response<List<FileResponse>>>(
                new Response<List<FileResponse>>("upload files success", 200, data,0),
                HttpStatus.OK
        );

//      cách 2 dùng @Buider
//      return ResponseEntity.ok(new Response.Builder<List<FileResponse>>().message("OK").data(data).status(200).build());
    }

    @GetMapping("/view/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = fileService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    // download file về máy
    @GetMapping(value = "/download/{fileName:.+}")
    public ResponseEntity<?> viewFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (contentType == null)
            contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @SuppressWarnings("unused")
    private FileResponse saveFile(MultipartFile file) {
        String fileName = fileService.storeFile(file);
        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath() // localhost:8080
                .path("/api/filemanager/view/") // localhost:8080/api/filemanager/view/
                .path(fileName) // localhost:8080/api/filemanager/view/anbc.jpg
                .toUriString();
        FileResponse data = new FileResponse(fileName, fileUri, file.getContentType(), file.getSize());
        return data;
    }
}
