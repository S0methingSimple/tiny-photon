package com.something.simple.tiny.photon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotonController {

//    private final PhotonService photonService;
//
//    public PhotonController(PhotonService photonService) {
//        this.photonService = photonService;
//    }

    @Autowired
    private PhotonService photonService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to tiny-photon";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/photo")
    public Collection<Photo> get() {
        return photonService.get();
    }

    @GetMapping("/photo/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = photonService.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photo/{id}")
    public void delete(@PathVariable String id) {
        Photo photo = photonService.remove(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

//    @PostMapping("/photo-old")
//    public Photo createOld(@RequestBody @Valid Photo photo) {
//        photo.setId(UUID.randomUUID().toString());
//        photonService.put(photo.getId(), photo);
//        return photo;
//    }

    @PostMapping("/photo")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photonService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}
