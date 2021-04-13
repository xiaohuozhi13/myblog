package com.mzb.service;

import com.mzb.po.User;

public interface UserService {

    User checkUser(String username,String password);
}
