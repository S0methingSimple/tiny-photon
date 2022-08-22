package com.something.simple.tiny.photon.repository;

import com.something.simple.tiny.photon.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotonRepository extends CrudRepository<Photo, Integer> {


}
