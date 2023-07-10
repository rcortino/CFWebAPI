package com.skapps.cfweb.services;

import com.skapps.cfweb.dtos.AlbumDTO;
import com.skapps.cfweb.dtos.PhotoDTO;

import java.util.List;

public interface PhotoService {

    List<AlbumDTO> getAlbums(long userId, String userToken);
    List<PhotoDTO> getPhotos(long userId, String userToken, int albumId);
}
