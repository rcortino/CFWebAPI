package com.skapps.cfweb.dao;

import com.skapps.cfweb.entities.Album;
import com.skapps.cfweb.entities.Photo;

import java.util.List;

public interface PhotoDAO {

    List<Album> getAlbums();
    List<Photo> getPhotos(int albumId);
}
