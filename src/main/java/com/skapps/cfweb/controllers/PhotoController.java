package com.skapps.cfweb.controllers;

import com.skapps.cfweb.dtos.AlbumDTO;
import com.skapps.cfweb.dtos.PhotoDTO;
import com.skapps.cfweb.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/photos")
public class PhotoController implements BaseController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/{userId}/getAlbums", method = RequestMethod.GET, produces = JSON)
    public List<AlbumDTO> getAlbums(@PathVariable("userId") int userId, @RequestParam(value = "userToken") String userToken) {
        return photoService.getAlbums(userId, userToken);
    }

    @RequestMapping(value = "/{userId}/getAlbumPhotos/{albumId}", method = RequestMethod.GET, produces = JSON)
    public List<PhotoDTO> getPhotos(@RequestParam(value = "userToken") String userToken, @PathVariable("albumId") int albumId, @PathVariable("userId") int userId) {
        return photoService.getPhotos(userId, userToken, albumId);
    }

}
