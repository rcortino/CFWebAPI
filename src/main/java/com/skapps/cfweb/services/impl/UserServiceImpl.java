package com.skapps.cfweb.services.impl;

import com.skapps.cfweb.dao.CfWebDAO;
import com.skapps.cfweb.dtos.AuthRequestDTO;
import com.skapps.cfweb.dtos.RegistrationApprovalDTO;
import com.skapps.cfweb.dtos.UserDTO;
import com.skapps.cfweb.dtos.UserRegistrationDTO;
import com.skapps.cfweb.entities.User;
import com.skapps.cfweb.exceptions.AuthenticationException;
import com.skapps.cfweb.objects.Token;
import com.skapps.cfweb.services.UserService;
import com.skapps.cfweb.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.AccountLockedException;

public class UserServiceImpl implements UserService {

    @Autowired
    private CfWebDAO cfWebDao;
    @Override
    public void registerUser(UserRegistrationDTO request) {

    }

    @Override
    public void approveRegistration(RegistrationApprovalDTO request) {

    }

    @Override
    public UserDTO authenticateUser(AuthRequestDTO request) throws AuthenticationException, AccountLockedException {
        if (!RedisUtils.AccountIsLocked(request.getCredentials().getUsername())) {
            User user = cfWebDao.authenticate(request.getCredentials().getUsername(), request.getCredentials().getPassword());
            if (user == null ) {
               RedisUtils.UpdateFailedLoginCount(request.getCredentials().getUsername(), true);
               throw new AuthenticationException();
            } else {
                Token token = new Token();
                UserDTO userDTO = new UserDTO(user, token);
                RedisUtils.registerUserToken(userDTO);
                RedisUtils.UpdateFailedLoginCount(request.getCredentials().getUsername(), false);
                return userDTO;
            }
        } else {
            throw new AccountLockedException();
        }
    }
}
