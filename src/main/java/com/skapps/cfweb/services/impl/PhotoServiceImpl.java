package com.skapps.cfweb.services.impl;

import com.skapps.cfweb.dao.PhotoDAO;
import com.skapps.cfweb.dtos.AlbumDTO;
import com.skapps.cfweb.dtos.PhotoDTO;
import com.skapps.cfweb.entities.Album;
import com.skapps.cfweb.entities.Photo;
import com.skapps.cfweb.services.PhotoService;
import com.skapps.cfweb.utils.DTOUtils;
import com.skapps.cfweb.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDAO photoDao;
    private RedisUtils redisUtils = new RedisUtils();
    @Override
    public List<AlbumDTO> getAlbums(long userId, String userToken) {
        if (redisUtils.validateToken(userId, userToken)) {
            List< Album> albums = photoDao.getAlbums();
            return DTOUtils.GetAlbumDTOs(albums);
        }
        return null;
    }

    @Override
    public List<PhotoDTO> getPhotos(long userId, String userToken, int albumId) {
        return null;
    }
}
