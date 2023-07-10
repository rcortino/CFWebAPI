package com.skapps.cfweb.entities;

import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;

@Entity
@Table (name = "cfw_albumn")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "album_id")
    int albumId;

    @Column(name="album_name")
    private String name;

    @Column(name = "album_description")
    private String description;

    @Column(name = "album_thumbnail")
    private String thumbnailPath;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
