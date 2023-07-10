package com.skapps.cfweb.controllers;

import com.skapps.cfweb.dtos.BookDTO;
import com.skapps.cfweb.services.DigitalLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dlib")
public class DigitalLibraryController implements BaseController{

    @Autowired
    private DigitalLibraryService digitalLibraryService;
    @RequestMapping(value = "/{userId}/getNewest", method = RequestMethod.GET, produces = JSON)
    public List<BookDTO> getNewestBooks(@RequestParam("userToken") String userToken) {
        return null;
    }


}
