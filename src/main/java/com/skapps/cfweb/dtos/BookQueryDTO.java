package com.skapps.cfweb.dtos;

import java.io.Serializable;

public class BookQueryDTO implements Serializable {

    private String userToken;
    private AuthorDTO author;
    private String title;
    private boolean isLike;
    private GenreDTO genre;
}
