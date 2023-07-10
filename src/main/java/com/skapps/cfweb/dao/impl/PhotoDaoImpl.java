package com.skapps.cfweb.dao.impl;

import com.skapps.cfweb.dao.PhotoDAO;
import com.skapps.cfweb.entities.Album;
import com.skapps.cfweb.entities.Photo;
import com.skapps.hibernate.EntityQuery;
import com.skapps.hibernate.params.EqualParam;
import com.skapps.hibernate.params.Param;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PhotoDaoImpl implements PhotoDAO {

    @Autowired
    private SessionFactory cfwebSessionFactory;

    @Override
    public List<Album> getAlbums() {
        EntityQuery<Album> query = new EntityQuery<>(Album.class, cfwebSessionFactory);
        return query.performListQuery(null);
    }

    @Override
    public List<Photo> getPhotos(int albumId) {
        EntityQuery<Photo> query = new EntityQuery<>(Photo.class, cfwebSessionFactory);
        List<Param> params = new ArrayList<>();
        params.add(new EqualParam<Photo>("albumId", albumId));
        return query.performListQuery(params);
    }
}
