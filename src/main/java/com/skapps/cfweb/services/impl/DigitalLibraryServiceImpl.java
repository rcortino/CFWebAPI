package com.skapps.cfweb.services.impl;

import com.skapps.cfweb.dao.DigitalLibraryDAO;
import com.skapps.cfweb.services.DigitalLibraryService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;

public class DigitalLibraryServiceImpl implements DigitalLibraryService {

    @Autowired
    private DigitalLibraryDAO dlibDao;

}

