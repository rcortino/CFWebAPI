package com.skapps.cfweb.controllers;

import com.skapps.cfweb.dtos.AuthorDTO;
import com.skapps.cfweb.dtos.BookDTO;
import com.skapps.cfweb.dtos.BookQueryDTO;
import com.skapps.cfweb.dtos.GenreDTO;
import com.skapps.cfweb.services.DigitalLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dlib")
public class DigitalLibraryController implements BaseController{

    @Autowired
    private DigitalLibraryService digitalLibraryService;
    @RequestMapping(value = "/{userId}/getNewest", method = RequestMethod.GET, produces = JSON)
    @ResponseBody
    public List<BookDTO> getNewestBooks(@RequestParam("userToken") String userToken, @PathVariable("userId") int userId) {
        System.out.println(userId);
        System.out.println(userToken);
        return null;
    }

    @RequestMapping(value = "/{userId}/getQueue", method = RequestMethod.GET, produces = JSON)
    @ResponseBody
    public List<BookDTO> getQueue(@RequestParam("userToken") String userToken, @PathVariable("userId") int userId) {
        return null;
    }

    @RequestMapping (value = "/getAuthors", method = RequestMethod.GET, produces = JSON)
    @ResponseBody
    public List<AuthorDTO> getAuthors() {
        return null;
    }

    @RequestMapping(value = "/getGenres", method = RequestMethod.GET, produces = JSON)
    @ResponseBody
    public List<GenreDTO> getGenres() {
        return null;
    }

    @RequestMapping(value = "/{userId}/getBookQueryResults", method = RequestMethod.POST, consumes = JSON, produces = JSON)
    @ResponseBody
    public List<BookDTO> getQueryResults(@RequestBody BookQueryDTO queryRequest, @PathVariable("userId") int userId) {
        return null;
    }
}
