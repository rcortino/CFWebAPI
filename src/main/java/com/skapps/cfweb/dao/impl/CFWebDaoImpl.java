package com.skapps.cfweb.dao.impl;

import com.skapps.cfweb.dao.CfWebDAO;
import com.skapps.cfweb.entities.User;
import com.skapps.cfweb.exceptions.AuthenticationException;
import com.skapps.hibernate.params.EqualParam;
import com.skapps.hibernate.params.Param;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import com.skapps.hibernate.EntityQuery;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

public class CFWebDaoImpl implements CfWebDAO {

    @Autowired
    private SessionFactory cfwebSessionFactory;

    @Override
    public User authenticate(String username, String password) throws AuthenticationException {
        EntityQuery<User> query = new EntityQuery<>(User.class, cfwebSessionFactory);
        List<Param> params = new ArrayList<>();
        params.add(new EqualParam<User>("username", username));
        User user = query.getSingleResult(params);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) return user;
        return null;
    }


}
