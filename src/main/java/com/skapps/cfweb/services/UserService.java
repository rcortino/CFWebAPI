package com.skapps.cfweb.services;

import com.skapps.cfweb.dtos.AuthRequestDTO;
import com.skapps.cfweb.dtos.RegistrationApprovalDTO;
import com.skapps.cfweb.dtos.UserDTO;
import com.skapps.cfweb.dtos.UserRegistrationDTO;

import javax.security.auth.login.AccountLockedException;

public interface UserService {
    void registerUser(UserRegistrationDTO request);
    void approveRegistration(RegistrationApprovalDTO request);
    UserDTO authenticateUser(AuthRequestDTO request) throws AccountLockedException;
}
