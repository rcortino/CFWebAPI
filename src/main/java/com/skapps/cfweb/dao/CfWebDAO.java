package com.skapps.cfweb.dao;

import com.skapps.cfweb.entities.User;
import com.skapps.cfweb.exceptions.AuthenticationException;

public interface CfWebDAO {

    User authenticate (String username, String password);
}
