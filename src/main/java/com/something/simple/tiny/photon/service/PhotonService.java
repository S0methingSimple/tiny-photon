package com.something.simple.tiny.photon.service;

import com.something.simple.tiny.photon.model.Photo;
import com.something.simple.tiny.photon.repository.PhotonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PhotonService {

    @Autowired
    private PhotonRepository photonRepository;

    public Iterable<Photo> get() {
        return photonRepository.findAll();
    }

    public Photo get(Integer id) {
        return photonRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        photonRepository.deleteById(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setData(data);
        photo.setContentType(contentType);

        photonRepository.save(photo);
        return photo;
    }
}
