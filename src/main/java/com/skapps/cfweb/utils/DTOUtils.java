package com.skapps.cfweb.utils;

import com.skapps.cfweb.dtos.AlbumDTO;
import com.skapps.cfweb.entities.Album;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class DTOUtils {

    public static List<AlbumDTO> GetAlbumDTOs(List<Album> albums) {
        List<AlbumDTO> albumDTOList = new ArrayList<>();
        for (Album album : albums) {
            albumDTOList.add(GetAlbumDTO(album));
        }
        return albumDTOList;
    }

    public static AlbumDTO GetAlbumDTO(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setAlbumId(album.getAlbumId());
        albumDTO.setAlbumName(album.getName());
        albumDTO.setAlbumDescription(album.getDescription());
        albumDTO.setThumbnailPath(album.getThumbnailPath());
        return albumDTO;
    }


}
