package com.skapps.cfweb.dao.impl;

import com.skapps.cfweb.dao.CfWebDAO;
import com.skapps.cfweb.dao.Params;
import com.skapps.cfweb.dao.Query;
import com.skapps.cfweb.entities.User;
import com.skapps.cfweb.exceptions.AuthenticationException;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

public class CFWebDaoImpl implements CfWebDAO {

    @Autowired
    private SessionFactory cfwebSessionFactory;

    @Override
    public User authenticate(String username, String password) throws AuthenticationException {
        Query<User> query = new Query<>(User.class, cfwebSessionFactory);
        Params params = new Params();
        params.addParam("username", username);
        User user = query.getSingleResult(params);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) return user;
        return null;
    }
}
